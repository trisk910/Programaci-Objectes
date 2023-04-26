package Business;
import Persistance.PeliDAO;
import Persistance.PlayerScoreListDAO;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager { //te la info i gestiona les preguntes.
    private PeliDAO peliDAO;
    private PlayerScoreListDAO scoreListDAO;

    private ArrayList<Peli> peliArrayList;
    public GameManager(PeliDAO peliDAO, PlayerScoreListDAO scoreListDAO) {
        this.peliDAO = peliDAO;
        this.scoreListDAO = scoreListDAO;
        this.peliArrayList = PeliDAO.getPelis();
    }

   public ArrayList<Peli> getRandomTittle() {
        ArrayList<Peli> peliArrList = new ArrayList<>();
        for(int i = 0; i < 1; i++) {
            peliArrList.add(peliArrayList.get(getRandomNumber(0, peliArrayList.size())));
        }
        return peliArrList;
    }
    public ArrayList<Peli> get2RandomTittle() {
        ArrayList<Peli> peliArrList = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            peliArrList.add(peliArrayList.get(getRandomNumber(0, peliArrayList.size())));
        }
        return peliArrList;
    }
    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double getDoubleRandomNumber(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max + 1);
    }

}
