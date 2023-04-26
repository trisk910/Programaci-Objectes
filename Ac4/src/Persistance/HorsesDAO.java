package Persistance;

import Business.Horse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HorsesDAO {

    public static ArrayList<Horse> getHorses(){
        ArrayList<Horse> horses = new ArrayList<>();
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get("./horses.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            String name = jsonArray.get(i).getAsJsonObject().get("nom").getAsString();
            int minSpeed = jsonArray.get(i).getAsJsonObject().get("velocitat_minima").getAsInt();
            int maxSpeed = jsonArray.get(i).getAsJsonObject().get("velocitat_maxima").getAsInt();
            horses.add(new Horse(name, minSpeed, maxSpeed));
        }
        return horses;
    }



}
