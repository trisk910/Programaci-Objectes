package Presentation;

import Business.GameManager;
import Business.Peli;
import Business.Player;
import Business.PlayerWithScore;
import Persistance.PlayerScoreListDAO;
import imageDisplayer.ImageDisplayer;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {
    private MainMenu mainMenu;

    private GameManager gameManager;

    static ArrayList<Player> playerScoreList;

    public UIController(MainMenu mainMenu, GameManager gameManager) {
        this.mainMenu = mainMenu;
        this.gameManager = gameManager;
    }
    public void startQuiz () {
        String name;
        boolean saveScore = false;
        mainMenu.Show();

        Scanner scn = new Scanner(System.in);
        try {
            name = scn.nextLine();
        } catch (Exception e) {
            return;
        }
        playerScoreList = PlayerScoreListDAO.getPlayers();
        /*for(int i = 0; i < playerScoreList.size(); i++) {
                System.out.println("nameUIController: " + playerScoreList.get(i).getName());
        }*/
        int playerIndex = Player.checkPlayerName(name, playerScoreList);
        if(playerIndex>-1)
            System.out.println("Oh! you wanna try again?");
        else {
            System.out.println("Oh! A new Player!");
            int questionFlag = 0;
            while (questionFlag == 0) {
                try{
                    System.out.println("Do you want to save your score? (y/n)");
                    String answer = scn.nextLine();
                    if (answer.equals("y")) {
                        saveScore = true;
                        questionFlag = 1;
                    } else if (answer.equals("n")) {
                        saveScore = false;
                        questionFlag = 1;
                    } else {
                        System.out.println("Please answer with y or n");
                    }
                } catch (Exception e) {
                    System.out.println("Please answer with y or n");
                }
            }
        }
        int currentQuestion = 0;
        boolean failed = false;
        String answer;
        int playerScore = 0;
        ArrayList<Peli> peliArrayList;
        int questionFlag = 0;
        while(!failed) {
            if(!failed){
                currentQuestion++;
                peliArrayList = gameManager.get2RandomTittle();
                System.out.println();
                System.out.println("----- QUESTION " + currentQuestion + " -----");
                System.out.println("Which movie recieved better ratings?");
                System.out.println("a) " + peliArrayList.get(0).getTitle());
                System.out.println("b) " + peliArrayList.get(1).getTitle());
                float scoreTitle1 = peliArrayList.get(0).getScore();
                float scoreTitle2 = peliArrayList.get(1).getScore();
                String correctAnswer;
                questionFlag = 0;
                if(scoreTitle1>scoreTitle2)
                    correctAnswer = "a";
                else
                    correctAnswer = "b";
                while(questionFlag == 0) {
                    try{
                        System.out.println("Your answer: ");
                        answer = scn.nextLine();
                        if (answer.equals("a") || answer.equals("b")) {
                            if (answer.equals(correctAnswer)) {
                                System.out.println("Correct!");
                                ImageDisplayer displayer = new ImageDisplayer();
                                displayer.DisplayImageWithText(peliArrayList.get(0));
                                displayer.DisplayImageWithText(peliArrayList.get(1));
                                playerScore++;
                            } else {
                                System.out.println("Incorrect! I am sorry " + name + " you're eliminated!");
                                System.out.println(peliArrayList.get(0).getTitle()+": "+peliArrayList.get(0).getScore());
                                System.out.println(peliArrayList.get(1).getTitle()+": "+peliArrayList.get(1).getScore());
                                failed = true;
                                break;
                            }
                            questionFlag = 1;
                        } else
                            System.out.println("Please answer with a or b");

                    } catch (Exception e) {
                        System.out.println("Please answer with a or b");
                    }
                }
            }
            if(!failed) {
                currentQuestion++;
                peliArrayList = gameManager.get2RandomTittle();
                System.out.println();
                System.out.println("----- QUESTION " + currentQuestion + " -----");
                System.out.println("When was "+ peliArrayList.get(0).getTitle()+" released?");
                System.out.println("a) " + peliArrayList.get(0).getReleaseDate());
                System.out.println("b) " + peliArrayList.get(1).getReleaseDate());
                String correctAnswer2 = String.valueOf('a');
                questionFlag = 0;
                while(questionFlag == 0) {
                    try{
                        System.out.println("Your answer: ");
                        answer = scn.nextLine();
                        if (answer.equals("a") || answer.equals("b")) {
                            if (answer.equals(correctAnswer2)) {
                                System.out.println("Correct!");
                                playerScore++;
                            } else {
                                System.out.println("Incorrect! I am sorry " + name + " you're eliminated!");
                                System.out.println(peliArrayList.get(0).getTitle()+": "+peliArrayList.get(0).getReleaseDate());
                                failed = true;
                                break;
                            }
                            questionFlag = 1;
                        }else
                            System.out.println("Please answer with a or b");
                    } catch (Exception e) {
                        System.out.println("Please answer with a or b");
                    }
                }

            }

            if(!failed){
                currentQuestion++;
                peliArrayList = gameManager.getRandomTittle();
                System.out.println();
                System.out.println("----- QUESTION " + currentQuestion + " -----");
                System.out.println("What was the budget of "+ peliArrayList.get(0).getTitle()+" ?");
                double budget = peliArrayList.get(0).getBudget();
                double budget2 = GameManager.getDoubleRandomNumber(100000000, 600000000);
                System.out.println("A) " + budget2);
                System.out.println("B) " + budget);
                questionFlag = 0;
                while(questionFlag == 0) {
                    try {
                        System.out.println("Your answer: ");
                        answer = scn.nextLine();
                        if (answer.equals("a") || answer.equals("b")) {
                            if (answer.equals("b")) {
                                System.out.println("Correct!");
                                playerScore++;
                            } else {
                                System.out.println("Incorrect! I am sorry " + name + " you're eliminated!");
                                failed = true;
                                break;
                            }
                            questionFlag = 1;
                        }else
                            System.out.println("Please answer with a or b");
                    } catch (Exception e) {
                        System.out.println("Please answer with a or b");
                    }
                }
            }
        }
        System.out.println("\nYou finished the quiz with a score of "+playerScore+" points.");
        if(playerIndex>-1) {

            if(playerScoreList.get(playerIndex) instanceof PlayerWithScore) {

                PlayerWithScore pws = (PlayerWithScore) playerScoreList.get(playerIndex);

                if(playerScore>pws.getScore()) {
                    pws.setScore(playerScore);
                    System.out.println("Congratulations, that was a high score!");
                    PlayerScoreListDAO.scoreListJSONUpdater(playerScoreList);
                }
            }
        }else{
            if(!saveScore)
            {
                Player pws = new Player(name);
                playerScoreList.add(pws);
                PlayerScoreListDAO.scoreListJSONUpdater(playerScoreList);
            }else {
                PlayerWithScore pws = new PlayerWithScore(name, playerScore);
                playerScoreList.add(pws);
                PlayerScoreListDAO.scoreListJSONUpdater(playerScoreList);
            }
        }

    }

}
