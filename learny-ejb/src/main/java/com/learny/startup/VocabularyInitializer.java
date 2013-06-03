package com.learny.startup;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.dao.local.EnWordDaoLocal;
import com.learny.parser.VocabularyFileImporter;
import com.learny.persistence.entity.vocabulary.DeWord;
import com.learny.persistence.entity.vocabulary.EnWord;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class VocabularyInitializer {

    @Inject
    private DeWordDaoLocal deWordDao;

    @Inject
    private EnWordDaoLocal enWordDao;

    @PostConstruct
    public void initVocabulary() {

        InputStream inputStream = this.getClass().getResourceAsStream("/test_data/de-en_test.txt");
        List<DeWord> deWords = VocabularyFileImporter.parse(inputStream);

        for (DeWord deWord : deWords) {
            Set<EnWord> persistedEnWords = new HashSet<>();
            for (EnWord enWord : deWord.getEnWords()) {
                EnWord persistedEnWord = enWordDao.findByValue(enWord.getValue());
                if (persistedEnWord == null) {
                    persistedEnWord = enWordDao.saveOrUpdate(enWord);
                }
                persistedEnWords.add(persistedEnWord);
            }
            deWord.getEnWords().clear();
            deWord.setEnWords(persistedEnWords);
            deWordDao.saveOrUpdate(deWord);
        }

    }

}
