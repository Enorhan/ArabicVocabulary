package com.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vocabulary {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String word;
    private String transliteration;
    private String meaning;
    private String sentence;
    private String sentenceTranslation;
}
