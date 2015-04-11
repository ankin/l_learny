package com.learny.importer;

import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;

import com.learny.model.DeExample;
import com.learny.model.DeWord;
import com.learny.model.EnExample;
import com.learny.model.EnWord;
import com.mongodb.MongoClient;

public class MongoLoader {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        Morphia morphia = new Morphia(new Mapper());
        morphia.map(DeWord.class);
        morphia.map(EnWord.class);
        morphia.map(DeExample.class);
        morphia.map(EnExample.class);
        Datastore datastore = morphia.createDatastore(mongoClient, "learny");

        InputStream inputStream = MongoLoader.class.getResourceAsStream("/test_data/de-en_test.txt");
        List<DeWord> deWords = VocabularyFileParser.parse(inputStream);

        for (DeWord deWord : deWords) {

            for (ListIterator<EnWord> iterator = deWord.getEnWords().listIterator(); iterator.hasNext();) {
                EnWord enWord = iterator.next();
                EnWord persistedEnWord = datastore.createQuery(EnWord.class).field("value").equal(enWord.getValue())
                        .get();
                if (persistedEnWord != null && enWord.equals(persistedEnWord)) {
                    iterator.remove();
                    iterator.add(persistedEnWord);
                } else {
                    datastore.save(enWord);
                }

                for (ListIterator<DeExample> exampleIterator = deWord.getDeExamples().listIterator(); exampleIterator
                        .hasNext();) {
                    DeExample deExample = exampleIterator.next();
                    DeExample existingDeExample = datastore.createQuery(DeExample.class).field("value")
                            .equal(deExample.getValue()).get();
                    if (existingDeExample != null) {
                        exampleIterator.remove();
                        exampleIterator.add(existingDeExample);
                    } else {
                        EnExample existingEnExample = datastore.createQuery(EnExample.class).field("value")
                                .equal(deExample.getEnExample().getValue()).get();
                        if (existingEnExample != null) {
                            deExample.setEnExample(existingEnExample);
                        } else {
                            datastore.save(deExample.getEnExample());
                        }
                        datastore.save(deExample);

                    }

                }

            }

            datastore.save(deWord);
        }
    }
}
