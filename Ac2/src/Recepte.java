import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Recepte {
    private ArrayList<Ingredient> ingredientsList;
    private String name;
    private String[] steps;
    private int[] timers;

    public Recepte(String name, ArrayList<Ingredient> ingredientsList, String[] steps, int[] timers) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.steps = steps;
        this.timers = timers;
    }
    public static ArrayList<Recepte> fromJson(String jsonString) {
        ArrayList<Recepte> receptes = new ArrayList<>();

        Gson gson = new Gson();

        JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            String name = jsonArray.get(i).getAsJsonObject().get("name").getAsString();

            ArrayList<Ingredient> ingredients = new ArrayList<>();

            JsonArray ingArray = jsonArray.get(i).getAsJsonObject().get("ingredients").getAsJsonArray();
            for(int z = 0; z <ingArray.size(); z++){
                JsonObject ingJSON = ingArray.get(z).getAsJsonObject();
                Ingredient ing = new Ingredient(ingJSON.get("quantity").getAsString(),
                        ingJSON.get("name").getAsString(),
                        ingJSON.get("type").getAsString());
                ingredients.add(ing);
            }

            JsonArray stepsArray = jsonArray.get(i).getAsJsonObject().get("steps").getAsJsonArray();
            String[] steps = gson.fromJson(stepsArray, String[].class);

            JsonArray timersArray = jsonArray.get(i).getAsJsonObject().get("timers").getAsJsonArray();
            int[] timers = gson.fromJson(timersArray, int[].class);

            receptes.add(new Recepte(name, ingredients,steps,timers));
        }
        return receptes;
    }
    public ArrayList<Ingredient> getIngredientsList() {
        return ingredientsList;
    }
    public String getName() {
        return name;
    }
    public String[] getSteps() {
        return steps;
    }

    public int[] getTimers() {
        return timers;
    }

}
