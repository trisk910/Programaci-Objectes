package Business;


public class PlayerWithScore extends Player {
    private int score;

    public PlayerWithScore(String name, int score) {
        super(name);
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
