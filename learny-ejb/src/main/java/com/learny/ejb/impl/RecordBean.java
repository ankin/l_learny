package com.learny.ejb.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.local.RecordLocal;
import com.learny.persistence.entity.Record;

@Stateless
@Named("recordBean")
public class RecordBean implements RecordLocal {

    @Override
    public List<Record> getRecords(Integer startCount) {
        throw new RuntimeException();
    }

}
