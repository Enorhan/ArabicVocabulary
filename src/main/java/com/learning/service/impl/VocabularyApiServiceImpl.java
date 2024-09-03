package com.learning.service.impl;

import com.learning.entity.Vocabulary;
import com.learning.service.VocabularyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
public class VocabularyApiServiceImpl implements VocabularyApiService {

    @Value("${yandex.api.base.url}")
    private String apiUrl;

    @Value("${yandex.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public VocabularyApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Vocabulary fetchVocabularyFromApi() {
        // Construct the URL with the API key and other necessary parameters
        String word = "example";  // Replace with the actual word you want to look up
        String url = String.format("%s/lookup?key=%s&lang=en-ru&text=%s", apiUrl, apiKey, word);

        try {
            // Make the API request and get the response
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null) {
                Vocabulary vocabulary = new Vocabulary();
                // Parsing the response based on Yandex Dictionary API structure
                vocabulary.setWord(word);  // Assuming you're searching for a specific word
                vocabulary.setMeaning(response.get("def").toString());  // Simplified; adjust based on actual response structure
                // Additional parsing based on the response structure
                return vocabulary;
            } else {
                throw new RuntimeException("API response is null");
            }
        } catch (RestClientException e) {
            System.err.println("Error fetching vocabulary from API: " + e.getMessage());
            return null;
        }
    }
}