package com.learny.persistence.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.learny.persistence.entity.core.ModificationDateEntity;
import com.learny.persistence.interfaces.Commentable;

@XmlRootElement
public class Record extends ModificationDateEntity implements Commentable {

    private User user;

    private List<Word> words;

    private List<Rule> rules;

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
