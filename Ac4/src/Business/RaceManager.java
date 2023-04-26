package Business;

import java.util.ArrayList;

public class RaceManager {
    public void CreateRace(ArrayList<Horse> horseList) {
        for(int i = 0; i < horseList.size(); i++){
            horseList.get(i).start();
        }
    }
}
