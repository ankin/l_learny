package com.learny.ejb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.local.LearningLineLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Rule;
import com.learny.persistence.entity.Word;

@Stateless
@Named
public class LearningLineBean implements LearningLineLocal {

    @Override
    public List<Record> getRecords(Integer startCount) {
        List<Record> records = new ArrayList<Record>();
        for (int i = 0; i < 5; i++) {
            Record record = new Record();
            record.setDateCreated(new Date());
            record.setWords(getMockWords());
            record.setRules(getMockRules());
            records.add(record);
        }

        return records;
    }

    private List<Word> getMockWords() {
        List<Word> mockWords = new ArrayList<Word>();
        Word word = new Word();
        word.setSource("das Auto");
        word.setTarget("автомобиль");
        mockWords.add(word);
        word = new Word();
        word.setSource("gehen");
        word.setTarget("идти");
        mockWords.add(word);
        word = new Word();
        word.setSource("der House");
        word.setTarget("дом");
        mockWords.add(word);
        word = new Word();
        word.setSource("heute");
        word.setTarget("сегодня");
        mockWords.add(word);
        word = new Word();
        word.setSource("morgen");
        word.setTarget("завтра");
        mockWords.add(word);
        return mockWords;
    }

    private List<Rule> getMockRules() {
        List<Rule> mockRules = new ArrayList<Rule>();
        Rule rule = new Rule();
        rule.setText("Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris"
                + "condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod."
                + " Donec sed odio dui.");
        mockRules.add(rule);
        return mockRules;
    }
}
