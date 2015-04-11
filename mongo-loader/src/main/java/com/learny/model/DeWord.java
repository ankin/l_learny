package com.learny.model;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "germanWords", noClassnameStored = true)
public class DeWord extends AbstractGenderWord {

    @Reference
    private List<EnWord> enWords = new ArrayList<>();

    @Reference
    private List<DeExample> deExamples = new ArrayList<>();

    public List<EnWord> getEnWords() {
        return enWords;
    }

    public void setEnWords(List<EnWord> en_words) {
        this.enWords = en_words;
    }

    public List<DeExample> getDeExamples() {
        return deExamples;
    }

    public void setDeExamples(List<DeExample> de_examples) {
        this.deExamples = de_examples;
    }

    public void addDeExample(DeExample de_example) {
        deExamples.add(de_example);
    }

}
