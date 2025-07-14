package com.example.dictionaryconversion.service;

import com.example.dictionaryconversion.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService{
    private final DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl() {
        this.dictionaryRepository = new DictionaryRepository();
    }

    @Override
    public String translate(String word) {
        return dictionaryRepository.findByWord(word);
    }
}
