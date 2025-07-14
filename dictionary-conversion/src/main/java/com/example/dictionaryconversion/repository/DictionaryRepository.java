package com.example.dictionaryconversion.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class DictionaryRepository {
    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
        dictionary.put("dog", "con chó");
        dictionary.put("cat", "con mèo");
    }

    public String findByWord(String word) {
        if (word == null) return null;
        return dictionary.get(word.toLowerCase().trim());
    }
}
