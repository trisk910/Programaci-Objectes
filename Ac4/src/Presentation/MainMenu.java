package Presentation;

import java.util.Scanner;

public class MainMenu {

    public int ShowMenu() {

        System.out.println("1-Start race");
        System.out.println("2-Check race");
        System.out.println("3-Exit");

        Scanner scn = new Scanner(System.in);

        try {
            int option = scn.nextInt();
            return option;
        } catch (Exception e) {
            return 0;
        }

    }
}
