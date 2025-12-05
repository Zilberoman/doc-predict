package com.zilberoman.doc.predict.endpoints;

import com.zilberoman.doc.predict.services.DiseasePredictionService;
import com.zilberoman.doc.predict.services.DoctorAdviserService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("index")
public class IndexResource {

    // index.qute.html
    @Inject
    Template index;

    // prediction.qute.html
    @Inject
    Template prediction;

    @Inject
    DiseasePredictionService predictionService;

    @Inject
    DoctorAdviserService doctorAdviserService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getForm()  {
        return index.instance();
    }

    @POST
    @Path("submit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance submit(@FormParam("symptoms") String symptoms) {
        String predictedDisease = predictionService.predictDisease(symptoms);
        return prediction.data("doctor", doctorAdviserService.determineDoctor(predictedDisease));
    }
}
