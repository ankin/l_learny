package com.learny.ejb.service.local;

import java.util.List;

import javax.ejb.Local;

import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.Word;

@Local
public interface SearchLocal {

    List<Word> search(String value, Language language);

}
