package Business;

import java.util.ArrayList;

public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static int checkPlayerName(String name, ArrayList<Player> playerScoreList){
        int index = -1;
        for (int i = 0; i < playerScoreList.size(); i++) {
            //System.out.println("nameInCheckPlayer: "+playerScoreList.get(i).getName());
            if (playerScoreList.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }
}
