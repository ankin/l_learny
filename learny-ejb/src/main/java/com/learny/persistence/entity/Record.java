package com.learny.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.ModificationDateEntity;
import com.learny.persistence.interfaces.Commentable;

@XmlRootElement
@Entity
@Table(name = Record.TABLE_NAME)
@NamedQueries(value = { @NamedQuery(name = Record.QUERY_BY_USER_UUID,
                                    query = "select o from Record o where o.user.uuid =:"
        + Record.PARAM_USER_ID) })
public class Record extends ModificationDateEntity implements Commentable {

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
    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinTable(name = Comment.TABLE_NAME + UNDERSCORE + TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = Comment.TABLE_NAME + UNDERSCORE + ID) })
    private List<Comment> comments;

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


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String getObjectType() {
        return "RECORD";
    }

}
