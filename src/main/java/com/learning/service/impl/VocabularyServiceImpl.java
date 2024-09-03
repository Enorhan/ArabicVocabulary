package com.learning.service.impl;

import com.learning.entity.Vocabulary;
import com.learning.repository.VocabularyRepository;
import com.learning.service.VocabularyApiService;
import com.learning.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private VocabularyApiService vocabularyApiService;

    @Override
    public List<Vocabulary> getDailyVocabulary(int count) {
        List<Vocabulary> allWords = vocabularyRepository.findAll();

        // Fetch additional words from the API if necessary
        if (allWords.size() < count) {
            for (int i = allWords.size(); i < count; i++) {
                Vocabulary vocab = vocabularyApiService.fetchVocabularyFromApi();
                vocabularyRepository.save(vocab);
                allWords.add(vocab);
            }
        }

        return new Random().ints(0, allWords.size())
                .distinct()
                .limit(count)
                .mapToObj(allWords::get)
                .collect(Collectors.toList());
    }
}
