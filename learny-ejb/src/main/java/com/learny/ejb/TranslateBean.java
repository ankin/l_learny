package com.learny.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.persistence.entity.Word;

@Stateless
@LocalBean
@Named
public class TranslateBean {

	public List<Word> translate(List<String> sources) {
		List<Word> words = new ArrayList<Word>();
		for (int i = 0; i < 5; i++) {
			Word word = new Word();
			word.setSource("source" + i);
			word.setTarget("target" + i);
			words.add(word);
		}
		return words;
	}

}
