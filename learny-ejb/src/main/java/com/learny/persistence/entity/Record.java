package com.learny.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.ModificationDateEntity;
import com.learny.persistence.interfaces.Commentable;

@Entity
@Table(name = Record.TABLE_NAME)
@NamedQueries(value = { @NamedQuery(name = Record.QUERY_BY_USER_UUID,
                                    query = "select o from Record o where o.user.uuid =:" + Record.PARAM_USER_ID) })
public class Record extends ModificationDateEntity implements Commentable {

    private static final long serialVersionUID = 5861441534010949510L;

    public final static String TABLE_NAME = "RECORD";

    public final static String QUERY_BY_USER_UUID = "Record.queryByUserUuid";
    public final static String PARAM_USER_ID = "userId";

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = User.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private User user;

    @Transient
    private List<Word> words;

    @Transient
    private List<Rule> rules;

    @JsonIgnore
    @OneToMany(targetEntity = RecordComment.class, mappedBy = "record", fetch = FetchType.LAZY)
    private List<RecordComment> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<RecordComment> getComments() {
        return comments;
    }

    public void setComments(List<RecordComment> comments) {
        this.comments = comments;
    }

    @Override
    public String getObjectType() {
        return "RECORD";
    }

}
