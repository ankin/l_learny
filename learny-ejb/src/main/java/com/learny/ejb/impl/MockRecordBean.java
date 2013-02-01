package com.learny.ejb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.learny.ejb.local.RecordLocal;
import com.learny.persistence.entity.Comment;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.Rule;
import com.learny.persistence.entity.User;
import com.learny.persistence.entity.Word;

@Stateless
@Named("mockRecordBean")
public class MockRecordBean implements RecordLocal {

    @Override
    public List<Record> getRecords(Integer startCount) {
        List<Record> records = new ArrayList<Record>();
        try {
            Record record = new Record();
            User user = new User();
            user.setFirstName("Karim");
            user.setLastName("Jones");
            record.setUser(user);
            record.setDateCreated(new Date());
            record.setWords(getMockWords());
            record.setRules(getMockRules());
            record.setComments(getMockComments());
            records.add(record);

            user = new User();
            user.setFirstName("Shilla");
            user.setLastName("Razimer");
            record.setUser(user);
            record.setDateCreated(new Date());
            record.setWords(getMockWords());
            record.setRules(getMockRules());
            record.setComments(getMockComments());
            records.add(record);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return records;
    }

    private List<Word> getMockWords() {
        List<Word> mockWords = new ArrayList<Word>();
        Word word = new Word();
        word.setSource("das Auto");
        word.setTarget("автомобиль");
        mockWords.add(word);
        word = new Word();
        word.setSource("gehen");
        word.setTarget("идти");
        mockWords.add(word);
        word = new Word();
        word.setSource("das House");
        word.setTarget("дом");
        mockWords.add(word);
        word = new Word();
        word.setSource("heute");
        word.setTarget("сегодня");
        mockWords.add(word);
        word = new Word();
        word.setSource("morgen");
        word.setTarget("завтра");
        mockWords.add(word);
        return mockWords;
    }

    private List<Rule> getMockRules() {
        List<Rule> mockRules = new ArrayList<Rule>();
        Rule rule = new Rule();
        rule.setText("Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris"
                + "condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod."
                + " Donec sed odio dui.");
        mockRules.add(rule);
        return mockRules;
    }

    private List<Comment> getMockComments() throws InterruptedException {
        List<Comment> mockComments = new ArrayList<Comment>();
        Comment comment = new Comment();
        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Bolvak");
        comment.setUser(user);
        comment.setText("This is cool! Thanks!");
        comment.setDateCreated(new Date());
        mockComments.add(comment);
        comment = new Comment();
        user = new User();
        user.setFirstName("Peter");
        user.setLastName("Atewar");
        comment.setUser(user);
        comment.setText("Sorry, i didn't get last rule. Could somebody please explain it?");
        comment.setDateCreated(new Date());
        Thread.sleep(500);
        mockComments.add(comment);
        comment = new Comment();
        user = new User();
        user.setFirstName("Jack");
        user.setLastName("Black");
        comment.setUser(user);
        comment.setText("It's easy, just understand it! :)");
        comment.setDateCreated(new Date());
        mockComments.add(comment);
        return mockComments;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
