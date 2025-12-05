package com.zilberoman.doc.predict.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@SystemMessage("You are professional doctor")
@ApplicationScoped
public interface DiseasePredictionService {

    @UserMessage("""
        Try to predict disease from the following list of symptoms: {symptoms}.
        Return only one predicted disease as a single word without extra symbols in lowercase.
    """)
    String predictDisease(String symptoms);
}
