package com.learny.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.dto.Translation;
import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Word;

@Stateless
@Named("translatorBean")
public class TranslatorBean implements TranslatorLocal {

    @Override
    public void translate(List<Word> words) {
        for (Word word : words) {
            Translation translation = new Translation("Перевод слова " + word.getOriginal(),
                    "Расширенное описание слова " + word.getOriginal() + " и примеры.");
            word.addTranslation(translation);
        }
    }
}
