package com.zilberoman.doc.predict.endpoints;

import com.zilberoman.doc.predict.dto.PredictionRequest;
import com.zilberoman.doc.predict.dto.PredictionResponse;
import com.zilberoman.doc.predict.services.DiseasePredictionService;
import com.zilberoman.doc.predict.services.DoctorAdviserService;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/predict")
public class PredictionResource {

    @Inject
    DiseasePredictionService predictionService;

    @Inject
    DoctorAdviserService doctorAdviserService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public PredictionResponse predictDisease(@Nonnull PredictionRequest request) {
        String predictedDisease = predictionService.predictDisease(request.getSymptoms());
        return new PredictionResponse(predictedDisease,
                doctorAdviserService.determineDoctor(predictedDisease));
    }
}
