package com.learny.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.learny.dto.Translation;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.vocabulary.DeWord;
import com.learny.persistence.entity.vocabulary.EnWord;

@Stateless
@Named("translatorBean")
public class TranslatorBean implements TranslatorLocal {

    @Inject
    private DeWordDaoLocal deWordDao;

    @Override
    public void translate(List<DeWord> words, Language language) {
        for (DeWord word : words) {
            translate(word, language);
        }
    }

    @Override
    public void translate(DeWord word, Language language) {
        switch (language) {
        case ENG:

            for (EnWord enWord : word.getEnWords()) {
                Translation translation = new Translation(enWord.getValue(), enWord.getDescription(), word.getGender(),
                        word.getType());
                word.addTranslation(translation);
            }
            break;
        case RUS:

            break;
        }

    }
}
