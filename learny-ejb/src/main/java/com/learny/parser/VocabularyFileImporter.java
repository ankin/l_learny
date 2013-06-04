package com.learny.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.learny.persistence.entity.vocabulary.AbstractDefaultWord;
import com.learny.persistence.entity.vocabulary.DeExample;
import com.learny.persistence.entity.vocabulary.DeWord;
import com.learny.persistence.entity.vocabulary.EnExample;
import com.learny.persistence.entity.vocabulary.EnWord;
import com.learny.persistence.entity.vocabulary.Gender;
import com.learny.persistence.entity.vocabulary.Type;

/**
 * 
 * noun - іменник
 * verb - дієслово
 * adj - adjective - прикметник
 * adv - adverb - прислівник
 * prep - preposition - прийменник
 * pron - pronoun - займенник
 * conj - conjunction - сполучник
 * 
 */

public class VocabularyFileImporter {

    private final static String FEMINIM = "{f}";
    private final static String MASKULIM = "{m}";
    private final static String NEUTRUM = "{n}";
    private final static String PLURAL = "{pl}";

    private final static String ESC = "[.]*";
    private final static String NOUN = "noun" + ESC;
    private final static String VERB = "verb" + ESC;
    private final static String ADJ = "adj" + ESC;
    private final static String ADV = "adv" + ESC;
    private final static String PREP = "prep" + ESC;
    private final static String PRON = "pron" + ESC;
    private final static String CONJ = "conj" + ESC;

    private final static String DESC_REGEXP = "\\[.+\\]";
    private final static String ABBR_REGEXP = "<.+>";

    private final static String GENDER_REGEXP = "\\{.{1,2}\\}";

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/andrii.kinash/tmp/de-en_test.txt");
        parse(fileInputStream);
    }

    public static List<DeWord> parse(InputStream finputStream) {
        List<DeWord> deWords = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(finputStream));) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = new String(line.getBytes("UTF-8"));
                String[] strings = line.split("\\t");

                Type type = getType(strings[strings.length - 1]);

                if (type == null) {
                    System.out.println("=====> Type not found:" + line);
                    //maybe it is example?
                    if (!deWords.isEmpty()) {
                        DeWord prevDeWord = deWords.get(deWords.size() - 1);
                        updateExample(prevDeWord, strings);
                    }
                    continue;
                }

                String firstPart = strings[0];
                DeWord deWord = new DeWord();
                updateWord(deWord, firstPart, type);

                if (deWord.getValue() == null) {
                    //maybe it is example?
                    DeWord prevDeWord = deWords.get(deWords.size() - 1);
                    updateExample(prevDeWord, strings);
                    continue;
                }

                if (deWords.contains(deWord)) { // it is just another translation
                    int index = deWords.indexOf(deWord);
                    deWord = deWords.get(index);
                }

                EnWord enWord = new EnWord();
                updateWord(enWord, strings[1], type);
                deWord.getEnWords().add(enWord);

                if (!deWords.contains(deWord)) {
                    deWords.add(deWord);
                }

                StringBuilder builder = new StringBuilder();
                builder.append("+ ");
                builder.append(deWord.getValue());
                builder.append(" ");
                builder.append(deWord.getType());
                builder.append(" {");
                builder.append(deWord.getGender());
                builder.append("} desc.");
                builder.append(deWord.getDescription());
                builder.append(" abbr.");
                builder.append(deWord.getAbbreviation());

                int count = 1;
                for (EnWord englWord : deWord.getEnWords()) {
                    builder.append(" ");
                    builder.append(count);
                    builder.append(". ");
                    builder.append(englWord.getValue());
                    builder.append(" ");
                    builder.append(englWord.getType());
                    builder.append(" desc.");
                    builder.append(englWord.getDescription());
                    builder.append(" abbr.");
                    builder.append(englWord.getAbbreviation());
                    count++;
                }

            }

        } catch (IOException e) {

        }

        return deWords;
    }

    private static void updateExample(DeWord prevDeWord, String[] strings) {
        System.out.println("=====> Maybe example?:" + strings[0]);
        String exampleStr = strings[0].replaceAll(ABBR_REGEXP, "").replaceAll(DESC_REGEXP, "")
                .replaceAll(GENDER_REGEXP, "").trim();
        if (exampleStr.contains(prevDeWord.getValue()) && !exampleStr.equals(prevDeWord.getValue())) {
            //yes it is!
            DeExample de_example = new DeExample();
            de_example.setValue(exampleStr);
            String enExampleStr = strings[1].replaceAll(ABBR_REGEXP, "").replaceAll(DESC_REGEXP, "")
                    .replaceAll(GENDER_REGEXP, "").trim();
            EnExample en_example = new EnExample();
            en_example.setValue(enExampleStr);
            de_example.setEnExample(en_example);
            prevDeWord.addDeExample(de_example);
            System.out.println("+[Ex.] " + prevDeWord.getValue() + ": " + de_example.getValue() + " => "
                    + de_example.getEnExample().getValue());
        } else {
            System.out.println("--------- Example not added: " + exampleStr);
        }
    }

    private static Type getType(String type) {
        if (type.matches(NOUN)) {
            return Type.NOUN;
        } else if (type.matches(VERB)) {
            return Type.VERB;
        } else if (type.matches(ADJ)) {
            return Type.ADJ;
        } else if (type.matches(ADV)) {
            return Type.ADV;
        } else if (type.matches(PREP)) {
            return Type.PREP;
        } else if (type.matches(PRON)) {
            return Type.PRON;
        } else if (type.matches(CONJ)) {
            return Type.CONJ;
        }
        return null;
    }

    private static void updateWord(AbstractDefaultWord abstractWord, String firstPart, Type type) {

        if (firstPart.contains("(sich)")) {
            firstPart = firstPart.replaceAll("[()]", "");
        } else if (firstPart.startsWith("(")) {
            firstPart = firstPart.replaceAll("\\(.+\\)", "");
        }

        abstractWord.setValue(firstPart);
        updateAbbreviation(abstractWord);
        updateDescription(abstractWord);

        if (abstractWord instanceof DeWord && Type.NOUN.equals(type)) {
            DeWord deWord = (DeWord) abstractWord;
            String word = deWord.getValue();
            String cleanWord = null;
            if (word.endsWith(FEMINIM)) {
                cleanWord = word.replace(FEMINIM, "").trim();
                deWord.setGender(Gender.F);
            } else if (word.endsWith(MASKULIM)) {
                cleanWord = word.replace(MASKULIM, "").trim();
                deWord.setGender(Gender.M);
            } else if (word.endsWith(NEUTRUM)) {
                cleanWord = word.replace(NEUTRUM, "").trim();
                deWord.setGender(Gender.N);
            } else if (word.endsWith(PLURAL)) {
                cleanWord = word.replace(PLURAL, "").trim();
                deWord.setGender(Gender.PL);
            }
            if (cleanWord == null) {
                abstractWord.setValue(null);
                return;
            }
            abstractWord.setValue(cleanWord);
        }
        abstractWord.setType(type);

    }

    private static void updateDescription(AbstractDefaultWord abstractWord) {
        String word = abstractWord.getValue();
        String squareBracketLeft = "[";
        String squareBracketRight = "]";
        String description = getInnerString(word, "\\" + squareBracketLeft, "\\" + squareBracketRight,
                squareBracketLeft, squareBracketRight);
        word = word.replaceAll(DESC_REGEXP, "").trim();
        abstractWord.setValue(word);
        if (description != null && !description.isEmpty()) {
            abstractWord.setDescription(description);
        }
    }

    private static void updateAbbreviation(AbstractDefaultWord abstractWord) {
        String word = abstractWord.getValue();
        String squareBracketLeft = "<";
        String squareBracketRight = ">";
        String abbreviation = getInnerString(word, "" + squareBracketLeft, "" + squareBracketRight, squareBracketLeft,
                squareBracketRight);
        word = word.replaceAll(ABBR_REGEXP, "").trim();
        abstractWord.setValue(word);
        if (abbreviation != null && !abbreviation.isEmpty()) {
            abstractWord.setAbbreviation(abbreviation);
        }
    }

    private static String getInnerString(String word, String regexpLeft, String regexpRight, String left, String right) {
        if (word.contains(left)) {
            if (word.split(regexpLeft).length == 2) {
                String description = word.substring(word.indexOf(left));
                description = description.replaceAll(regexpLeft, "");
                description = description.replaceAll(regexpRight, "");
                return description;
            }
        }
        return null;
    }

}
