package com.learning.service.impl;

import com.learning.entity.Vocabulary;
import com.learning.service.VocabularyScheduler;
import com.learning.service.VocabularyService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularySchedulerImpl implements VocabularyScheduler  {

    private final VocabularyService vocabularyService;
    private final JavaMailSender javaMailSender;

    public VocabularySchedulerImpl(VocabularyService vocabularyService, JavaMailSender javaMailSender) {
        this.vocabularyService = vocabularyService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Scheduled(cron = "0 0 8 * * ?") //every day at 8 am
    public void sendDailyVocabularyEmail() {
        List<Vocabulary> dailyVocabulary = vocabularyService.getDailyVocabulary(15);
        StringBuilder content = new StringBuilder("Todays arabic vocabulary:\n\n");

        for (Vocabulary vocabulary : dailyVocabulary) {
            content.append(vocabulary.getWord())
                    .append(" (")
                    .append(vocabulary.getTransliteration())
                    .append(") - ")
                    .append(vocabulary.getMeaning())
                    .append("\nExample: ")
                    .append(vocabulary.getSentence())
                    .append(" (")
                    .append(vocabulary.getSentenceTranslation())
                    .append(")\n\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo();
        message.setSubject("Daily arabic Vocabulary");
        message.setText(content.toString());

        javaMailSender.send(message);
    }

}
