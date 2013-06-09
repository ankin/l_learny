package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.vocabulary.DeWord;

@Local
public interface TranslatorLocal {



    void translate(List<DeWord> words, Language language);

    void translate(DeWord word, Language language);

}
