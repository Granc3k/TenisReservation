package granc3k.semestralproject.reservationsystemtenis;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //saving and loading files
        //vars for code
        String command;
        boolean end = false;
        Reservations rezervace_current = new Reservations();
        Reservations rezervace_next = new Reservations();
        int thisWeek = LocalDateTime.now().getDayOfYear()/7 ;
        int nextWeek = (LocalDateTime.now().getDayOfYear()/7)+1;
        boolean switchedWeeks = false;

        //used for switching between codes
        Reservations rezervace = rezervace_current;
        //loading from files
        try{
            rezervace_current.loadFromFile(thisWeek);
           /* Arrays.stream(new File("./data/").listFiles())
                    .parallel()
                    .forEach(file -> {
                        try {
                            rezervace.loadFromFile(file.getName().replace(".json", ""));
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.err.println("Could not load file");
                        }
                    });*/
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor aktuálního týdne...");
        }
        try{
            rezervace_next.loadFromFile(nextWeek);
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor následujícího týdne...");
        }

        //main code
        System.out.println("Spouštím rezervační systém");
        while(!end){
            vypisMenu();
            System.out.print(">");
            command = sc.next();
            String[] parts = command.split(" ");
            try{//switch for commands
                switch (parts[0]) {
                    case "help" -> Commands.help();
                    case "res","1" -> Commands.res(rezervace, parts);
                    case "edit","2" -> Commands.editRes(rezervace, parts);
                    case "rem","3" -> Commands.remRes(rezervace, parts);
                    case "list","4" -> {
                        System.out.println("dnes -- pro výpis dneška\n"+
                                           "den -- pro výpis dne\n"+
                                           "tyden -- pro výpis týdne");
                        String decision = sc.next();
                            switch(decision){
                                case "dnes","today","t"->Commands.listForToday(rezervace,parts);
                                case "tyden","week","w"->Commands.listForWeek(rezervace,parts);
                                case "den","day","d"->Commands.listForDay(rezervace,parts);
                            }
                    }
                    case "isres","5" -> Commands.isRes(rezervace,parts);
                    case "endres","6" -> Commands.endRes(rezervace,parts);
                    case "switch","7" ->{
                        if(switchedWeeks){
                            rezervace_next = rezervace;
                            rezervace = rezervace_current;
                        }else {
                            rezervace_current = rezervace;
                            rezervace = rezervace_next;
                        }
                    }
                    case "exit" -> {
                        System.out.println("Zavírám program a ukládám data");
                        rezervace_current.saveToFile(thisWeek);
                        rezervace_next.saveToFile(nextWeek);
                        end = true;
                    }
                    default -> System.out.println("Zadal jste něco špatně!");
                }
            } catch (Exception e) {
                //e.printStackTrace();  //debug
                System.err.println("Něco se pokazilo...\n"+
                                    "zkuste to znovu");
            }
        }

    }
    public static void vypisMenu(){
        System.out.println("Rezervační systém pro Tenisové kurty:\n"+
        "1 - pro vytvoření rezervace\n"+
        "2 - pro editaci rezervace\n"+
        "3 - pro odstranění rezervace\n"+
        "4 - pro výpis rezervací\n"+
        "5 - pro zjištění, zda zadané místo je volné\n"+
        "6 - pro výpis ceny pro zaplacení rezervace\n"+
        "7 - pro přepnutí aktuálního týdne s následujícím\n"+
        "help - pro pomoc se zadáváním hodnot, nebo příkazů\n"+
        "exit - pro vypnutí programu");
    }
}
