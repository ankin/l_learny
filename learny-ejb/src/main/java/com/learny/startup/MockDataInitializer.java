package com.learny.startup;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.learny.ejb.dao.local.CommentDaoLocal;
import com.learny.ejb.dao.local.DeWordDaoLocal;
import com.learny.ejb.dao.local.EnWordDaoLocal;
import com.learny.ejb.dao.local.RecordDaoLocal;
import com.learny.ejb.dao.local.RuleDaoLocal;
import com.learny.ejb.dao.local.UserDaoLocal;
import com.learny.parser.VocabularyFileImporter;
import com.learny.persistence.entity.Language;
import com.learny.persistence.entity.Record;
import com.learny.persistence.entity.RecordComment;
import com.learny.persistence.entity.Role;
import com.learny.persistence.entity.Rule;
import com.learny.persistence.entity.User;
import com.learny.persistence.entity.vocabulary.DeWord;
import com.learny.persistence.entity.vocabulary.EnWord;

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
    private DeWordDaoLocal deWordDao;

    @Inject
    private EnWordDaoLocal enWordDao;

    @Inject
    private RuleDaoLocal ruleDao;

    @PostConstruct
    public void init() {
        initVocabulary();
        User user1 = createUser("james.bond@gmail.com", "007", "James", "Bond", Role.STUDENT, Language.ENG);

        User user2 = createUser("ivan.bolvan@gmail.com", "008", "Ivan", "Bolvan", Role.STUDENT, Language.RUS);
        User user3 = createUser("jsummer@gmail.com", "009", "John", "Summer", Role.NONE, Language.ENG);

        Record record = createRecord(user1, "abgreifen", "gehen", "Zimmer", "Geldautomat", "Abschnitt", "abgeschwächt");
        createRecordComment(record, user2, "Very interesting!");
        createRecordComment(record, user3, "What #2 means?");

        record = createRecord(user1, "Kokosnüsse", "Kokosmatte", "Kollege", "siebenmal");
        createRecordComment(record, user2, "Could you please explain the rule #1?");
        createRecordComment(record, user3, "No! =)");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DATE, 13);
        record.setDateCreated(calendar.getTime());
        recordDao.saveOrUpdate(record);

        record = createRecord(user1, "gemütlich", "Knopf", "Geldautomat");
        createRecordComment(record, user2, "Nice word list! So many of them!");
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 4);
        record.setDateCreated(calendar.getTime());
        recordDao.saveOrUpdate(record);

        record = createRecord(user1, "Zinne", "abgreifen", "gehen", "Kokosmatte", "knospen");
        createRecordComment(record, user3, "Does anybody can write ane example with word 'abwenden'?");
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 16);
        record.setDateCreated(calendar.getTime());
        recordDao.saveOrUpdate(record);

    }

    private User createUser(String email, String password, String firstName, String lastName, Role role,
            Language language) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setLanguage(language);
        return userDao.saveOrUpdate(user);
    }

    private Record createRecord(User user, String... words) {
        Record record = new Record();
        record.setUser(user);
        for (String word : words) {
            record.addWord(deWordDao.findByValue(word).get(0));
        }

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

    private Rule createRule(String text) {
        Rule rule = new Rule();
        rule.setText(text);
        return ruleDao.saveOrUpdate(rule);
    }

    private void initVocabulary() {
        InputStream inputStream = this.getClass().getResourceAsStream("/test_data/de-en_test.txt");
        List<DeWord> deWords = VocabularyFileImporter.parse(inputStream);

        for (DeWord deWord : deWords) {
            Set<EnWord> persistedEnWords = new HashSet<>();
            for (EnWord enWord : deWord.getEnWords()) {
                EnWord persistedEnWord = enWordDao.findByValue(enWord.getValue());
                if (persistedEnWord == null) {
                    persistedEnWord = enWordDao.saveOrUpdate(enWord);
                }
                persistedEnWords.add(persistedEnWord);
            }
            deWord.getEnWords().clear();
            deWord.setEnWords(persistedEnWords);
            deWordDao.saveOrUpdate(deWord);
        }

    }
}
