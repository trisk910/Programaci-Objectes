import Business.GameManager;
import Persistance.PeliDAO;
import Persistance.PlayerScoreListDAO;
import Presentation.MainMenu;
import Presentation.UIController;

public class Main {
    public static void main(String[] args) {
       /* ImageDisplayer displayer = new ImageDisplayer();
        Peli m1 = new Peli();

        displayer.DisplayImageWithText(m1);*/

        PeliDAO daoPeli = new PeliDAO();
        PlayerScoreListDAO daoScoreList = new PlayerScoreListDAO();

        GameManager GM = new GameManager(daoPeli, daoScoreList);

        MainMenu menu = new MainMenu();
        UIController controller = new UIController(menu,GM);
        controller.startQuiz();
    }
}