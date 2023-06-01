package granc3k.semestralproject.reservationsystemtenis.ui;
import granc3k.semestralproject.reservationsystemtenis.app.*;
import granc3k.semestralproject.reservationsystemtenis.utils.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
public class Main {
    public static Scanner sc = new Scanner(System.in);
    /**
     * main method for program
     * contains UI
     * calls other files and methods in them
     */
    public static void main(String[] args) throws IOException {
        //vars for code
        String command;
        boolean end = false;
        Reservations reservations_current = new Reservations();
        Reservations reservations_next = new Reservations();
        int thisWeek = LocalDateTime.now().getDayOfYear()/7 ;
        int nextWeek = (LocalDateTime.now().getDayOfYear()/7)+1;
        boolean switchedWeeks = false;
        String prompt = "current_"+thisWeek;
        boolean debug = false;
        boolean loaded = false;

        while(!loaded) {
            //choose load
            System.out.println("Jak chcete načíst soubory: bin/json");
            String dec = sc.next();
            switch (dec) {
                case "json" -> {
                    loadJson(reservations_current, reservations_next, thisWeek, nextWeek);
                    loaded = true;
                }
                case "bin" -> {
                    loadBin(reservations_current, reservations_next, thisWeek, nextWeek);
                    loaded = true;
                }
                default -> System.out.println("Zadal si něco špatně\n" +
                                                "zkus to znovu!!\n");
            }
        }

        //music to background
        System.out.println("Chcete pustit hudbičku ke kódu?? a/n");
        String dec = sc.next();
        if(dec.equals("a")) {
            Music.play("npc_move.wav", 0.5F);
        }

        //used for switching between variables
        Reservations reservations = reservations_current;

        //main code
        System.out.println("Spouštím rezervační systém");
        while(!end){
            vypisMenu();
            System.out.print(prompt+">");
            command = sc.next();
            String[] parts = command.split(" ");
            try{//switch for commands
                switch (parts[0]) {
                    case "help" -> Commands.help();
                    case "res","1" -> Commands.res(reservations, parts);
                    case "edit","2" -> Commands.editRes(reservations, parts);
                    case "rem","3" -> Commands.remRes(reservations, parts);
                    case "list","4" -> {
                        System.out.println("dnes -- pro výpis dneška\n"+
                                           "den -- pro výpis dne\n"+
                                           "tyden -- pro výpis týdne");
                        String decision = sc.next();
                            switch(decision){
                                case "dnes","today","t"->Commands.listForToday(reservations,parts);
                                case "tyden","week","w"->Commands.listForWeek(reservations,parts);
                                case "den","day","d"->Commands.listForDay(reservations,parts);
                            }
                    }
                    case "isres","5" -> Commands.isRes(reservations,parts);
                    case "endres","6" -> Commands.endRes(reservations,parts);
                    case "isfree","7"-> {
                        System.out.println("dnes -- pro výpis dneška\n" +
                                "den -- pro výpis dne\n" +
                                "tyden -- pro výpis týdne");
                        String decision = sc.next();
                        switch (decision) {
                            case "tyden", "week", "w" -> {
                                //System.out.println("Dolaďuje se :)");
                                Commands.whatIsFreeWeek(reservations, parts);
                                }
                            case "den", "day", "d" -> {
                                //System.out.println("Dolaďuje se :)");
                                Commands.whatIsFreeDay(reservations, parts);
                            }
                            case "dnes", "today", "t" -> {
                                //System.out.println("Dolaďuje se :)");
                                Commands.whatIsFreeToday(reservations);
                            }
                        }
                    }
                    case "switch","8" ->{
                        if(switchedWeeks){
                            //switch to current week
                            reservations_next = reservations;
                            reservations = reservations_current;
                            switchedWeeks=false;
                            prompt="current_"+thisWeek;
                        }else {
                            //switch to next week
                            reservations_current = reservations;
                            reservations = reservations_next;
                            switchedWeeks=true;
                            prompt="next_"+nextWeek;
                        }
                    }
                    case "save" ->{
                        System.out.println("Jak chcete uožit data: bin/json");
                        String in = sc.next();

                        switch(in){
                            case "bin"->{
                                reservations_current.saveToBin(thisWeek);
                                reservations_next.saveToBin(nextWeek);
                                System.out.println("Data byla uložena do binárního souboru");
                            } case "json"->{
                                reservations_current.saveToFile(thisWeek);
                                reservations_next.saveToFile(nextWeek);
                                System.out.println("Data byla uložena do .json");
                            }default -> System.out.println("Zadali jste něco špatně...");
                        }
                    }
                    case "exit" -> {
                        System.out.println("Zavírám program a ukládám data");
                        reservations_current.saveToFile(thisWeek);
                        reservations_next.saveToFile(nextWeek);
                        Music.play("left_the_chat.wav", 0.5F);
                        Thread.sleep(500);
                        end = true;
                    }
                    case "debug" -> debug=!debug;
                    default -> System.out.println("Zadal jste něco špatně!");
                }
            } catch (Exception e) {
                if(debug){e.printStackTrace();}  //debug
                System.err.println("Něco se pokazilo...\n"+
                                    "zkuste to znovu");
            }
            System.out.println();//prints out empty line
        }

    }
    /**
     * prints out menu
     */
    public static void vypisMenu(){
        System.out.println("------Rezervační systém pro Tenisové kurty------\n"+
        "\n"+
        "1 - pro vytvoření reservations\n"+
        "2 - pro editaci reservations\n"+
        "3 - pro odstranění reservations\n"+
        "4 - pro výpis rezervací\n"+
        "5 - pro zjištění, zda zadané místo je volné\n"+
        "6 - pro výpis ceny pro zaplacení reservations\n"+
        "7 - pro výpis volných časů\n"+
        "8 - pro přepnutí aktuálního týdne s následujícím\n"+
        "save - pro uložení do souborů\n"+
        "help - pro pomoc se zadáváním hodnot, nebo příkazů\n"+
        "exit - pro vypnutí programu");
    }
    /**
     * loads data to variables from json
     */
    public static void loadJson(Reservations reservations_current, Reservations reservations_next, int thisWeek, int nextWeek) throws IOException{
        System.out.println("Načítám data z .json souborů");
        //loading from files
        try{
            reservations_current.loadFromFile(thisWeek);
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor aktuálního týdne...");
        }
        try{
            reservations_next.loadFromFile(nextWeek);
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor následujícího týdne...");
        }
    }
    /**
     * loads data to variables from binary files
     */
    public static void loadBin(Reservations reservations_current, Reservations reservations_next, int thisWeek, int nextWeek) throws IOException{
        System.out.println("Načítám data z binárních souborů");
        //loading from files
        try{
            reservations_current.loadFromBin(thisWeek);
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor aktuálního týdne...");
        }
        try{
            reservations_next.loadFromBin(nextWeek);
        }catch (Exception e){
            //e.printStackTrace();  //for debug
            System.err.println("Nepodařilo se načíst soubor následujícího týdne...");
        }
    }
}
