package com.learny.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.User;

@Singleton
@Startup
public class MockDataInitializer {

    @EJB
    private UserDaoLocal userDao;

    @EJB
    private RecordDaoLocal recordDao;

    @EJB
    private CommentDaoLocal commentDao;

    @PostConstruct
    public void init() {
        User user1 = createUser("akinash@gmail.com", "Andrii", "Kinash");
        User user2 = createUser("ivan.bolvan@gmail.com", "Ivan", "Bolvan");
        User user3 = createUser("jsummer@gmail.com", "John", "Summer");

        Comment comment1 = createComment(user2, "Very interesting!");
        Comment comment2 = createComment(user3, "What #2 means?");
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);

        Record record1 = createRecord(user1);
        record1.setComments(comments);
        recordDao.saveOrUpdate(record1);
    }

    private User createUser(String email, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userDao.saveOrUpdate(user);
    }

    private Record createRecord(User user) {
        Record record = new Record();
        record.setUser(user);
        return recordDao.saveOrUpdate(record);
    }

    private Comment createComment(User user, String text) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setText(text);
        return commentDao.saveOrUpdate(comment);
    }

}
