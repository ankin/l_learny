package com.learny.ejb.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.service.local.TranslatorLocal;
import com.learny.persistence.entity.Word;

@Stateless
@Named("translatorBean")
public class TranslatorBean implements TranslatorLocal {

    @Override
    public void translate(List<Word> words) {
        for (Word word : words) {
            word.setTranslated(word.getOriginal() + "[перевод]");
        }
    }
}
