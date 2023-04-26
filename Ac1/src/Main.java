import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.print("Introdueix el nom del restaurant: ");

        Scanner scn = new Scanner(System.in);
        String inputBar = scn.nextLine();
        String restaurantFile = readFile(inputBar +".txt");
        int flagRead = 0;
        if(restaurantFile == "")
            flagRead = 1;

        while(flagRead == 1){
            System.out.print("Introdueix el nom del restaurant: ");
            inputBar = scn.nextLine();
            restaurantFile = readFile(inputBar +".txt");

            if(restaurantFile == "")
                flagRead = 1;
            else
                flagRead = 0;
        }
        System.out.println(restaurantFile);

        String[] taulesParts = restaurantFile.split(" ");
        Taula[]  taules = new Taula[taulesParts.length];

        for(int i = 0; i< taules.length; i++){
            String taula = taulesParts[i];
            taules[i] = Taula.parseTaula(taula);
        }

        double benificis = 0;

        Scanner scnMenu = new Scanner(System.in);
        boolean ok = false;
        int opcioMenu  = 0;
        while(!ok || opcioMenu !=4){
            System.out.println("-----");
            System.out.println("1. Mostra restaurant");
            System.out.println("2. Fes reserva");
            System.out.println("3. Mostra benificis");
            System.out.println("4. Sortir");

            try{
                opcioMenu = Integer.parseInt(scnMenu.nextLine());
                ok = true;
                if(ok == true){
                    switch (opcioMenu){
                        case 1:
                            for(int i = 0; i < taules.length; i++){
                                if(taules[i].isTerrasa()){
                                    System.out.print("*");
                                }
                                System.out.print(taules[i].getID() + ": ");
                                int setCadires = taules[i].getNumPersones();
                                for (int x = 0; x < taules[i].getNumCadires(); x++){
                                    if(setCadires<= 0){
                                        System.out.print("-");
                                    }else{
                                        System.out.print("O");
                                        setCadires--;
                                    }
                                }
                                System.out.println();
                            }
                            break;
                        case 2:
                            boolean rOk1 = false;
                            boolean rOk2 = false;
                            int numPersones = 0;
                            while (!rOk1){
                                Scanner reservaScn = new Scanner(System.in);
                                System.out.println("Quantes persones? ");
                                if(reservaScn.hasNextInt()) {
                                    numPersones = Integer.parseInt(reservaScn.nextLine());
                                    rOk1 = true;
                                }else{
                                    System.out.println("ERROR el valor introduit es incorrecta");
                                }
                            }
                            boolean rTerrassa = false;
                            while (!rOk2) {
                                System.out.println("Volen terrassa(s/n)? ");
                                Scanner scnReserva = new Scanner(System.in);

                                String rInput = scnReserva.nextLine();
                                if(rInput.equals("s")){
                                    rTerrassa = true;
                                    rOk2 = true;
                                }else if(rInput.equals("n")){
                                    rTerrassa = false;
                                    rOk2 = true;
                                }else {
                                    System.out.println("ERROR el valor introduit es incorrecta");
                                }
                            }
                            int idTaula = -1;
                            int guardarPos = -1;
                            int millorTaula = 9999;
                            int aux = 9999;
                            for(int z = 0; z < taules.length; z++){
                                millorTaula = taules[z].getNumCadires() - numPersones;
                                if(rTerrassa && (taules[z].getNumCadires() >= numPersones && taules[z].isTerrasa() && !taules[z].isReservat())){
                                    if(millorTaula == 0){
                                        guardarPos = z;
                                        z = taules.length;
                                        break;
                                    }else {
                                        if(aux > millorTaula) {
                                            aux = millorTaula;
                                            guardarPos = z;
                                        }
                                    }
                                }else{
                                    if(!rTerrassa && (taules[z].getNumCadires() >= numPersones && !taules[z].isTerrasa() && !taules[z].isReservat())){
                                        if(millorTaula == 0){
                                            guardarPos = z;
                                            z = taules.length;
                                            break;
                                        }else{
                                            if(aux > millorTaula) {
                                                aux = millorTaula;
                                                guardarPos = z;
                                            }
                                        }
                                    }
                                }
                            }
                            idTaula = Integer.parseInt(taules[guardarPos].getID());
                            if(guardarPos == -1){
                                System.out.println("ERROR no hi han taules disponibles");
                            }else{
                                taules[idTaula - 1].setNumPersones(numPersones);
                                taules[idTaula - 1].setReservat(true);
                                System.out.println("Taula " + idTaula + " reservada per a " + numPersones);
                                if(rTerrassa){
                                    benificis = benificis + numPersones * 10.5;
                                }else{
                                    benificis = benificis + numPersones * 9.5;
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Benificis: " + benificis);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("ERROR: Aquesta opcio no esta en el menu");
                            ok = false;

                    }
                }
            }
        	catch (Exception e){
                    System.out.println("ERROR: Aquesta opcio no esta en el menu");
                }
            }
        }
    public static String readFile(String filename){
        File file = new File(filename);
        Scanner scn;

        try{
            scn = new Scanner(file);
        }catch (Exception e){
            System.out.println("Restaurant not found");
            return "";
        }
        return scn.nextLine();
    }
}