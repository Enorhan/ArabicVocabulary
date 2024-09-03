package com.learning.service;

import com.learning.entity.Vocabulary;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VocabularyService {

    List<Vocabulary> getDailyVocabulary(int count);

}
