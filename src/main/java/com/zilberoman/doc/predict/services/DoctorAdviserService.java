package com.zilberoman.doc.predict.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.zilberoman.doc.predict.endpoints.PredictionResource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DoctorAdviserService {
    private static final Logger LOGGER = Logger.getLogger(PredictionResource.class);
    private static final String GENERAL_PRACTITIONER_ADVISE = "Visit general practitioner";
    private final Map<String, String> diseaseDoctorMap = new HashMap<>();


    @PostConstruct
    private void init()  {
        InputStream inputStream = PredictionResource.class.getResourceAsStream("/doctors.csv");

        if (inputStream == null) {
            throw new IllegalArgumentException("CSV file wasn't found");
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             CSVReader csvReader = new CSVReader(bufferedReader)) {
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                diseaseDoctorMap.put(row[0], String.format("%s(%s)", row[1], row[2]));
            }
        } catch (IOException | CsvValidationException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String determineDoctor(String disease) {
        return diseaseDoctorMap.getOrDefault(disease, GENERAL_PRACTITIONER_ADVISE);
    }
}
