package com.learning.controller;

import com.learning.entity.Vocabulary;
import com.learning.service.VocabularyApiService;
import com.learning.service.VocabularyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vocabulary")
public class VocabularyController {

    private final VocabularyApiService vocabularyApiService;
    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyApiService vocabularyApiService, VocabularyService vocabularyService) {
        this.vocabularyApiService = vocabularyApiService;
        this.vocabularyService = vocabularyService;
    }


    // Endpoint to get the daily vocabulary
    @GetMapping("/daily")
    public List<Vocabulary> getDailyVocabulary(@RequestParam(defaultValue = "15") int count) {
        return vocabularyService.getDailyVocabulary(count);
    }

}