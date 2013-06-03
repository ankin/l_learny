package com.learny.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.dto.Translation;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.Word;
import com.learny.persistence.entity.vocabulary.DeWord;
import com.learny.persistence.entity.vocabulary.EnWord;

@Stateless
@Named("translatorBean")
public class TranslatorBean implements TranslatorLocal {

    @Inject
    private DeWordDaoLocal deWordDao;

    @Override
    public void translate(List<Word> words, Language language) {
        for (Word word : words) {
            translate(word, language);
        }
    }

    @Override
    public void translate(Word word, Language language) {
        switch (language) {
        case ENG:
            DeWord deWord = deWordDao.findByValue(word.getOriginal());
            if (deWord == null) {
                Translation translation = new Translation("No translation", null);
                word.addTranslation(translation);
            } else {
                for (EnWord enWord : deWord.getEnWords()) {
                    Translation translation = new Translation(enWord.getValue(), enWord.getDescription());
                    word.addTranslation(translation);
                }
            }
            break;
        case RUS:

            break;
        }

    }
}
