package com.learny.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.learny.persistence.entity.core.IdEntity;

@Entity
@Table(name = Word.TABLE_NAME)
@NamedQueries(@NamedQuery(name = Word.QUERY_BY_ORIGINAL, query = "select o from Word o where o.original=:"
        + Word.PARAM_ORIGINAL))
public class Word extends IdEntity {

    private static final long serialVersionUID = 9064223559376466447L;

    public final static String TABLE_NAME = "WORD";

    public final static String QUERY_BY_ORIGINAL = "Word.queryByOriginal";
    public final static String PARAM_ORIGINAL = "original";

    @Column(name = "ORGINAL", nullable = false, unique = true)
    private String original;

    @Transient
    private String translated;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

}
