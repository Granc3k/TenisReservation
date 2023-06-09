package granc3k.semestralproject.reservationsystemtenis.utils;

import granc3k.semestralproject.reservationsystemtenis.app.Reservations;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
                    System.err.println(
                    "Počet hráčů byl zadán špatně !!!\n"+
                    "Zadejte kladné číslo !!!\n");

                }
            } catch (Exception e) {
                System.err.println(
                "Počet hráčů byl zadán špatně !!!\n"+
                "Zadejte kladné číslo !!!\n");
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
            System.out.println("Zadejte den v týdnu (slovně, či číselně): ");
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
            System.out.println("Číslo kurtu (1-6): ");
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
            System.out.println("Od kolika (ve tvaru **:**): ");
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
            System.out.println("Do kolika (ve tvaru **:**): ");
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
    public static boolean validateTime(String time) {
        String regex = "\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static int[] inputDate() {
        boolean inputted = false;
        String datum = "";
        int[] tydenDen = new int[2];
        while (!inputted) {
            System.out.println("Zadejte datum ve tvaru DD.MM.YYYY :");
            datum = sc.nextLine();
            if (validateDate(datum)) {
                inputted = true;
            } else {
                System.err.println("Datum bylo zadáno špatně!!!\n");
            }
        }
        int den = 0;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(datum, formatter);

            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayName = dayOfWeek.toString();
            den = Reservations.whichDay(dayName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tydenDen[0] = getWeekNumber(datum);
        tydenDen[1] = den;
        return tydenDen;
    }

    public static boolean validateDate(String date) {
        String regex = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        return Pattern.matches(regex, date);
    }

    public static int getWeekNumber(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = dateFormat.parse(dateString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return -1;
    }
}
