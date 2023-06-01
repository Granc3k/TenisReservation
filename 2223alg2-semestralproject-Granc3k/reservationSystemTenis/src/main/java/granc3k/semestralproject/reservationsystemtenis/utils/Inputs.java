package granc3k.semestralproject.reservationsystemtenis.utils;

import granc3k.semestralproject.reservationsystemtenis.app.Reservations;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inputs {
    public static Scanner sc = new Scanner(System.in);

    /**
     * input of customer
     */
    public static String inputCus(){
        String customer="";
        boolean in = false;
        while(!in) {
            System.out.println("Rezervující: ");
            try {
                customer = sc.next();
                in = true;
            } catch (Exception e) {
                System.err.println("Jméno zákazníka bylo zadáno špatně !!!\n");
            }
        }
        return customer;
    }
    /**
     * input of players
     */
    public static int inputPlayers(){
        int players=0;
        boolean in = false;
        while(!in) {
            System.out.println("Počet hráčů: ");
            try {
                players = sc.nextInt();
                if(players>0){
                    in = true;
                }else{
                    System.err.println("Počet hráčů byl zadán špatně !!!\n");
                }
            } catch (Exception e) {
                System.err.println("Počet hráčů byl zadán špatně !!!\n");
            }
        }
        return players;
    }
    /**
     * input of day
     */
    public static int inputDay(){
        String input;
        int day=0;
        boolean in = false;
        while(!in) {
            System.out.println("Zadejte den v týdnu: ");
            try {
                input = sc.next();
                day = Reservations.whichDay(input);
                in = true;
            } catch (Exception e) {
                System.err.println("Den byl zadán špatně !!!\n");
            }
        }
        return day;
    }
    /**
     * input of court number
     */
    public static int inputCourt(){
        int court = 0;
        boolean in = false;
        while(!in) {
            System.out.println("Číslo kurtu: ");
            try {
                court = sc.nextInt();
                if(court<7 && court>0){
                    in = true;
                }else{
                    System.err.println("Číslo kurtu bylo zadáno špatně !!!\n");
                }
            } catch (Exception e) {
                System.err.println("Číslo kurtu bylo zadáno špatně !!!\n");
            }
        }
        return court;
    }
    /**
     * inputs start time
     */
    public static int inputStart(){
        String input = "";
        int start = 0;
        boolean in = false;
        while(!in) {
            System.out.println("Od kolika: ");
            try {
                input = sc.next();
                if(validateTime(input)){
                    start = Reservations.hours(input);
                    in = true;
                }else{
                    System.err.println("Čas byl zadán špatně !!!\n");
                }
            } catch (Exception e) {
                System.err.println("Čas byl zadán špatně !!!\n");
            }
        }
        return start;
    }
    /**
     * inputs end time
     */
    public static int inputEnd(){
        String input = "";
        int end = 0;
        boolean in = false;
        while(!in) {
            System.out.println("Do kolika: ");
            try {
                input = sc.next();
                if(validateTime(input)){
                    end = Reservations.hours(input);
                    in = true;
                }else{
                    System.err.println("Čas byl zadán špatně !!!\n");
                }
            } catch (Exception e) {
                System.err.println("Čas byl zadán špatně !!!\n");
            }
        }
        return end;
    }
    /**
     * validates if time is in format XX:XX
     */
    public static boolean validateTime(String time){
        String regex = "\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

}
