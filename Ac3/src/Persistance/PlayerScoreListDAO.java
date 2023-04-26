package Persistance;

import Business.Player;
import Business.PlayerWithScore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayerScoreListDAO {

    public static ArrayList<Player> getPlayers() {
        String jsonString = null;
        try {
            jsonString = Files.readString(Paths.get("./score.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Player> playerScoreListDAOArrayList = new ArrayList<>();

        Gson gson = new Gson();

        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            String name = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
            try{
                int score = jsonArray.get(i).getAsJsonObject().get("score").getAsInt();
                //System.out.println("nameInJson: " + name + " scoreInJson: " + score);
                playerScoreListDAOArrayList.add(new PlayerWithScore(name, score));
            } catch (Exception e) {
                playerScoreListDAOArrayList.add(new Player(name));
               // System.out.println("nameInJson: " + name);
            }
        }

        return playerScoreListDAOArrayList;
    }

    public static void scoreListJSONUpdater(ArrayList<Player> playerScoreList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(playerScoreList);
        try {
            Files.writeString(Paths.get("./score.json"), jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
