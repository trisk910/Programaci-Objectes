import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Recepte> recepte;
    public static void main(String[] args) {
        String jsonString;
        try {
            jsonString = Files.readString(Paths.get("./Recipes.json"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
         recepte = Recepte.fromJson(jsonString);
        System.out.println("Carregades " + recepte.size() + " receptes!");

        Scanner scnMenu = new Scanner(System.in);
        boolean ok = false;
        int opcioMenu  = 0;
        while(!ok || opcioMenu !=3){
            System.out.println("------");
            System.out.println("1 - Consultar recepta");
            System.out.println("2 - Cerca per ingredient");
            System.out.println("3 - Sortir");
            try{
                opcioMenu = Integer.parseInt(scnMenu.nextLine());
                if(opcioMenu < 1 || opcioMenu > recepte.size())
                    System.out.println("Opció Invàldia");
                else
                   ok = true;
                if(ok == true){
                    switch (opcioMenu){
                        case 1:
                            System.out.println("Quina recepte vols consultar?");
                            int consulta = Integer.parseInt(scnMenu.nextLine());
                            consultarRecepte(consulta);
                            break;
                        case 2:
                            System.out.println("Quin ingredient vols cercar?");
                            Scanner scn = new Scanner(System.in);
                            String cercarIng = scn.nextLine();
                            cercarIngredients(cercarIng);
                            break;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Opció Invàldia");
            }
        }
    }
    private static void consultarRecepte(int consulta) {
        int idRecepte = consulta - 1;
        String r = recepte.get(idRecepte).getName();
        System.out.println(r);

        int nIngredient = recepte.get(idRecepte).getIngredientsList().size();
        System.out.println("Ingredients: " + nIngredient);

        String[] p = recepte.get(idRecepte).getSteps();
        System.out.println("Passos: " + p.length);

        int[] t = recepte.get(idRecepte).getTimers();
        int tempsTotal = 0;
        for(int i= 0; i< t.length; i++){
            tempsTotal +=  t[i];
        }
        System.out.println("Temps d'espera: " + tempsTotal);

        ArrayList<Ingredient> cIngredients = recepte.get(idRecepte).getIngredientsList();
        boolean celiacs = false;
        for(int i = 0; i < cIngredients.size(); i++){
            if(cIngredients.get(i).getType().equals("Dairy")){
                celiacs = true;
            }
        }
        if(celiacs)
            System.out.println("Conté làctics? Sí");
        else
            System.out.println("Conté làctics? No");
    }
    private static void cercarIngredients(String cercarIng){
        ArrayList<Ingredient> cIngs;
        boolean notFound = false;
        for(int c = 0; c < recepte.size(); c ++){
           cIngs = recepte.get(c).getIngredientsList();
           int skip = 0;
            for(int x = 0; x < recepte.get(c).getIngredientsList().size(); x++) {
                if(cIngs.get(x).getName().contains(cercarIng)){
                    notFound = true;
                    if(skip == 0) {
                        System.out.println(recepte.get(c).getName());
                        skip++;
                    }
                    System.out.println("\t" + recepte.get(c).getIngredientsList().get(x).getName() + ": " +
                            recepte.get(c).getIngredientsList().get(x).getQuantity());
                }
            }
        }
        if(!notFound)
            System.out.println("No recipes found");
    }
}