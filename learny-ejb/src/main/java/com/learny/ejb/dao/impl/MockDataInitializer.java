package com.learny.ejb.dao.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.RuleDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.ejb.dao.local.WordDaoLocal;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.RecordComment;
import com.learny.persistence.entity.Role;
import com.learny.persistence.entity.Rule;
import com.learny.persistence.entity.User;
import com.learny.persistence.entity.Word;

@Singleton
@Startup
public class MockDataInitializer {

    @Inject
    private UserDaoLocal userDao;

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private CommentDaoLocal commentDao;

    @Inject
    private WordDaoLocal wordDao;

    @Inject
    private RuleDaoLocal ruleDao;

    @PostConstruct
    public void init() {
        User user1 = createUser("james.bond@gmail.com", "007", "James", "Bond", Role.STUDENT);

        User user2 = createUser("ivan.bolvan@gmail.com", "008", "Ivan", "Bolvan", Role.STUDENT);
        User user3 = createUser("jsummer@gmail.com", "009", "John", "Summer", Role.NONE);

        Record record1 = createRecord(user1);
        createRecordComment(record1, user2, "Very interesting!");
        createRecordComment(record1, user3, "What #2 means?");



    }

    private User createUser(String email, String password, String firstName, String lastName, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        return userDao.saveOrUpdate(user);
    }

    private Record createRecord(User user) {
        Record record = new Record();
        record.setUser(user);
        record.addWord(createWord("das Auto"));
        record.addWord(createWord("das Haus"));
        record.addWord(createWord("gehen"));
        record.addWord(createWord("das Wasser"));
        record.addWord(createWord("die Zeit"));
        record.addWord(createWord("schreiben"));

        record.addRule(createRule("Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris"
                + "condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod."
                + " Donec sed odio dui."));
        return recordDao.saveOrUpdate(record);
    }

    private void createRecordComment(Record record, User user, String text) {
        RecordComment comment = new RecordComment();
        comment.setUser(user);
        comment.setText(text);
        comment.setRecord(record);
        commentDao.saveOrUpdate(comment);
    }

    private Word createWord(String original) {
        Word word = new Word();
        word.setOriginal(original);
        return wordDao.saveOrUpdate(word);
    }

    private Rule createRule(String text) {
        Rule rule = new Rule();
        rule.setText(text);
        return ruleDao.saveOrUpdate(rule);
    }
}
