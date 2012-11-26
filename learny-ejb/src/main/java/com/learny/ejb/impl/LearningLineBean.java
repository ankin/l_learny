package com.learny.ejb.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.local.LearningLineLocal;
import com.learny.persistence.entity.Record;

@Stateless
@Named("learningLineBean")
public class LearningLineBean implements LearningLineLocal {

    @Override
    public List<Record> getRecords(Integer startCount) {
        throw new RuntimeException();
    }

}
