package com.learny.ejb.service.local;

import java.util.Set;

import javax.ejb.Local;

import com.learny.persistence.entity.Word;

@Local
public interface TranslatorLocal {



    void translate(Set<Word> words);

}
