package com.learny.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.learny.persistence.entity.core.ModificationDateEntity;
import com.learny.persistence.interfaces.Commentable;

@XmlRootElement
@Entity
@Table(name = "RECORD")
public class Record extends ModificationDateEntity implements Commentable {

    @Id
    private Long id;

    @Transient
    private User user;

    @Transient
    private List<Word> words;

    @Transient
    private List<Rule> rules;

    @Transient
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
