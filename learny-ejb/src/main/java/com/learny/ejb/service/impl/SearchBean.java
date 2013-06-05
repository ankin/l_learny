package com.learny.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.service.local.SearchLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.Word;
import com.learny.persistence.entity.vocabulary.DeWord;

@Stateless
@Named("searchBean")
public class SearchBean implements SearchLocal {

    @Inject
    private DeWordDaoLocal deWordDao;

    @Inject
    private TranslatorLocal translator;

    @Override
    public List<Word> search(String value, Language language) {
        List<DeWord> deWords = deWordDao.search(value);
        List<Word> words = new ArrayList<>();
        for (DeWord deWord : deWords) {
            Word word = new Word(deWord.getValue());
            words.add(word);
        }
        translator.translate(words, language);
        return words;
    }
}
