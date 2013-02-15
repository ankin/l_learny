package com.learny.ejb.dao.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.RecordComment;
import com.learny.persistence.entity.Role;
import com.learny.persistence.entity.User;

@Singleton
@Startup
public class MockDataInitializer {

    @Inject
    private UserDaoLocal userDao;

    @Inject
    private RecordDaoLocal recordDao;

    @Inject
    private CommentDaoLocal commentDao;

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
        return recordDao.saveOrUpdate(record);
    }

    private Comment createRecordComment(Record record, User user, String text) {
        RecordComment comment = new RecordComment();
        comment.setUser(user);
        comment.setText(text);
        comment.setRecord(record);
        return commentDao.saveOrUpdate(comment);
    }

}
