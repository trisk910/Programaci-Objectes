import Business.RaceManager;
import Persistance.HorsesDAO;
import Presentation.MainMenu;
import Presentation.UIController;

public class Main {
    public static void main(String[] args) {
        HorsesDAO daoHorses = new HorsesDAO();
        MainMenu menu = new MainMenu();
        RaceManager gm = new RaceManager();
        UIController controller = new UIController(menu,daoHorses,gm);
        controller.start();
    }
}