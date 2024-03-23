package app.controllers;
import app.entities.Plante;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.PlanteMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel Rouvillain
 */

public class PlantPalController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("PlantPal", ctx -> index(ctx, connectionPool));
        app.get("PlantPal/createplant", ctx -> ctx.render("/PlantPal/createplant.html"));
        app.post("PlantPal/createplant", ctx -> createplant(ctx,connectionPool));
        app.post("PlantPal/deleteplante", ctx -> deleteplante(ctx, connectionPool));
        app.post("PlantPal/vand", ctx -> updateDate(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        User user = ctx.sessionAttribute("currentUser");
        try{
            List<Plante> planteList = PlanteMapper.getAllPlantsPerUser(user.getUserId(),connectionPool);

            ctx.render("/PlantPal/index.html", Map.of("planteList",planteList));

        } catch (Exception e) {
            ctx.attribute("message", "Husk at logge ind");
            ctx.render("/index.html");
        }


    }

    private static void deleteplante(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        try {
            int planteId = Integer.parseInt(ctx.formParam("planteId"));
            int vandTidId;
            String vandTidIdParam = ctx.formParam("vandTidId");
            if (vandTidIdParam != null && !vandTidIdParam.isEmpty()) {
                vandTidId = Integer.parseInt(vandTidIdParam);
            }

            PlanteMapper.deletePlanteAndVandtid(planteId, connectionPool);

            List<Plante> planteList = PlanteMapper.getAllPlantsPerUser(user.getUserId(),connectionPool);

            ctx.attribute("planteList", planteList);
            ctx.render("/PlantPal/index.html");

        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("/PlantPal/index.html");
        }
    }


    private static void updateDate(Context ctx, ConnectionPool connectionPool){
        User user = ctx.sessionAttribute("currentUser");
        try{
            int planteId = Integer.parseInt(ctx.formParam("planteId"));
            /*LocalDate dato = LocalDate.parse(ctx.formParam("planteDato"));
            Date sqlDate = Date.valueOf(dato);
                    */
            PlanteMapper.updateDato(planteId,connectionPool);


            List<Plante> planteList = PlanteMapper.getAllPlantsPerUser(user.getUserId(), connectionPool);
            ctx.attribute("planteList", planteList);
            ctx.render("/PlantPal/index.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void createplant(Context ctx,ConnectionPool connectionPool){

        User user = ctx.sessionAttribute("currentUser");
            try {
                String planteNavn = ctx.formParam("plantenavn");
                String placering = ctx.formParam("placering");
                PlanteMapper.addPlante(user,planteNavn,placering,connectionPool);
                List<Plante> planteList = PlanteMapper.getAllPlantsPerUser(user.getUserId(), connectionPool);
                ctx.render("/PlantPal/index.html", Map.of("planteList", planteList));
            } catch (DatabaseException e) {
                ctx.render("/PlantPal/createplant.html");
            }


    }

}
