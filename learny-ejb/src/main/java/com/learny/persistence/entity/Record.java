package com.learny.persistence.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.learny.persistence.entity.core.ModificationDateEntity;

@Entity
@Table(name = Record.TABLE_NAME)
@NamedQueries(value = { @NamedQuery(name = Record.QUERY_BY_USER_EMAIL, //
query = "select o from Record o" //
        + " left join fetch o.words" //
        + " left join fetch o.rules" //
        + " where o.user.email =:" + Record.PARAM_USER_EMAIL // 
        + " order by o.dateCreated desc") })
public class Record extends ModificationDateEntity {

    private static final long serialVersionUID = 5861441534010949510L;

    public final static String TABLE_NAME = "RECORD";

    public final static String QUERY_BY_USER_EMAIL = "Record.queryByUserUuid";
    public final static String PARAM_USER_EMAIL = "userEmail";

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = User.TABLE_NAME + UNDERSCORE + ID, nullable = false)
    private User user;

    @ManyToMany(targetEntity = Word.class, fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME + UNDERSCORE + Word.TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = Word.TABLE_NAME + UNDERSCORE + ID) })
    private Set<Word> words = new HashSet<>();

    @ManyToMany(targetEntity = Rule.class, fetch = FetchType.LAZY)
    @JoinTable(name = TABLE_NAME + UNDERSCORE + Rule.TABLE_NAME, joinColumns = { @JoinColumn(name = TABLE_NAME
            + UNDERSCORE + ID) }, inverseJoinColumns = { @JoinColumn(name = Rule.TABLE_NAME + UNDERSCORE + ID) })
    private Set<Rule> rules = new HashSet<>();

    @JsonIgnore
    @OneToMany(targetEntity = RecordComment.class, mappedBy = "record", fetch = FetchType.LAZY)
    private Set<RecordComment> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void addWord(Word word) {
        assert !words.contains(word) : "Word [UUID=" + word.getUuid() + "] already exists in Record [UUID=" + getUuid()
                + "]";
        if (!words.contains(word)) {
            words.add(word);
        }
    }

    public void removeWord(Word word) {
        assert words.contains(word) : "Word [UUID=" + word.getUuid() + "] doesn't exists in Record [UUID=" + getUuid()
                + "]";
        if (words.contains(word)) {
            words.remove(word);
        }
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        assert !rules.contains(rule) : "Rule [UUID=" + rule.getUuid() + "] already exists in Record [UUID=" + getUuid()
                + "]";
        if (!rules.contains(rule)) {
            rules.add(rule);
        }
    }

    public void removeRule(Rule rule) {
        assert rules.contains(rule) : "Rule [UUID=" + rule.getUuid() + "] doesn't exists in Record [UUID=" + getUuid()
                + "]";
        if (rules.contains(rule)) {
            rules.remove(rule);
        }
    }

    public Set<RecordComment> getComments() {
        return comments;
    }

    public void setComments(Set<RecordComment> comments) {
        this.comments = comments;
    }

}
