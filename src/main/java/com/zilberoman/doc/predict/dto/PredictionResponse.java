package com.zilberoman.doc.predict.dto;

public class PredictionResponse {
    private String disease;
    private String doctor;

    public PredictionResponse(String disease, String doctor) {
        this.disease = disease;
        this.doctor = doctor;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
