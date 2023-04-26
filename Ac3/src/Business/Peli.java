package Business;

import Persistance.PeliDAO;
import imageDisplayer.Displayable;

import java.util.ArrayList;

public class Peli implements Displayable{

    private double budget;
    //private int ID;
    private String posterURL;
    private String releaseDate;
    private String title;
    private float score;//vote_average

    public Peli(double budget, /*int ID,*/ String posterURL,String releaseDate, String title, float score) {
        this.budget = budget;
        //this.ID = ID;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.title = title;
        this.score = score;
    }



    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public float getScore() {
        return score;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String getDisplayText() {
        return title;
    }

    @Override
    public String getDisplayImage() {
        return "https://www.themoviedb.org/t/p/w300_and_h450_bestv2"+posterURL;
    }
}
