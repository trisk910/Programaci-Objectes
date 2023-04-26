package Presentation;

import Business.RaceManager;
import Business.Horse;
import Persistance.HorsesDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {

    private MainMenu menu;
    private HorsesDAO daoHorses;
    private RaceManager gm;
    public UIController(MainMenu menu, HorsesDAO daoHorses, RaceManager gm) {
        this.menu = menu;
        this.daoHorses = daoHorses;
        this.gm = gm;
    }

    public void start () {
        int option = -1;
        Scanner scn = new Scanner(System.in);
        ArrayList<Horse> horseList = daoHorses.getHorses();
        gm.CreateRace(horseList);
        while (option != 3) {
            option = menu.ShowMenu();
            switch (option) {
                case 1:
                   /* for (int i = 0; i < horseList.size(); i++) {
                       if(horseList.get(i).isAlive())
                           System.out.println("Thread is Alive");
                    }*/
                    boolean racing = false;
                    for (int x = 0; x < horseList.size(); x++) {
                        boolean checkRunningHorse = horseList.get(x).isRacing();
                        if(checkRunningHorse)
                            racing = true;
                    }
                    if(!racing) {
                        System.out.println("Starting new Race!");
                        for (int i = 0; i < horseList.size(); i++) {
                           horseList.get(i).setDistanceTraveled(0.0);
                           horseList.get(i).setIsFinished(false);
                           horseList.get(i).setRacing(true);
                        }
                    }else{
                        System.out.println("Cannot start new race yet!");
                    }
                    break;
                case 2:
                    for(int i = 0; i< horseList.size(); i++) {
                       double getHorsePosition = horseList.get(i).getDistanceTraveled();
                        System.out.println(horseList.get(i).getHorseName() + ":\t");
                        for(int a = 0; a < getHorsePosition; a++){
                            System.out.print("=");
                        }
                        System.out.print("\uD83D\uDC0E");
                        for(int x = 0; x < 100-getHorsePosition - 1; x++){
                            System.out.print("-");
                        }
                        System.out.print("\uD83D\uDE7E");
                        //System.out.print(getHorsePosition);
                        //System.out.println(horseList.get(i).getHorseName()+" " + horseList.get(i).isRacing() + " " + horseList.get(i).isFinished() +  horseList.get(i).getDistanceTraveled());
                        System.out.println();
                    }
                    System.out.println();
                    break;
                case 3:
                    for (int i = 0; i < horseList.size(); i++) {
                        horseList.get(i).interrupt();
                    }
                    break;
                default: System.out.println("Invalid option");
            }
        }
    }
}
