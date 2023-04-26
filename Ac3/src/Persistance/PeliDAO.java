package Persistance;

import Business.Peli;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PeliDAO {
    //aki es procesa la informació del json


    public static ArrayList<Peli> getPelis(){
        ArrayList<Peli> pelis = new ArrayList<>();
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get("./movies.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            double budget;
            try{
                budget = jsonArray.get(i).getAsJsonObject().get("budget").getAsDouble();
            }  catch (Exception e) {
                budget  = 0;
            }

           // int id = jsonArray.get(i).getAsJsonObject().get("id").getAsInt();
            String posterURL = jsonArray.get(i).getAsJsonObject().get("poster_path").getAsString();
            String releaseDate = jsonArray.get(i).getAsJsonObject().get("release_date").getAsString();
            String title = jsonArray.get(i).getAsJsonObject().get("title").getAsString();
            float score = jsonArray.get(i).getAsJsonObject().get("vote_average").getAsFloat();
            //String[] directors = new String[jsonArray.get(i).getAsJsonObject().get("directors").getAsJsonArray().size()];

            pelis.add(new Peli(budget,/*id,*/posterURL,releaseDate,title,score));
        }
        return pelis;
    }

}
