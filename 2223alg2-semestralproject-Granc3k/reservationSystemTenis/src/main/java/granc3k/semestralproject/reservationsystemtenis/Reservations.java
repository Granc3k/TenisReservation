package granc3k.semestralproject.reservationsystemtenis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Reservations {
    //price for one court for one hour
    static final int cena = 300;
    //list with reservations
    private ArrayList<Reservation> reservationList;
    //2D array with objects of time
    private final Times[][] reservedTimes;
    public static Scanner sc = new Scanner(System.in);

    /**
     * constructor for Reservations object
     */
    public Reservations() {
        this.reservationList = new ArrayList<Reservation>();
        this.reservedTimes = new Times[7][6];//7 days in week and 6 courts
        fillTimes();
    }

    /**
     * fills 2D array of object Times with -1
     */
    private void fillTimes(){
        for (int i = 0; i < reservedTimes.length; i++) {
            for(int j=0;j<reservedTimes[i].length;j++){
                reservedTimes[i][j]=new Times();
            }
        }
    }
    /**
     * makes reservation via input
     */
    public void makeRes() {
        System.out.println("Rezervující: ");
        String customer = sc.next();

        System.out.println("Počet hráčů: ");
        int players = sc.nextInt();

        System.out.println("Jaký den: ");
        int day = whichDay(sc.next());

        System.out.println("Kurt: ");
        int court = sc.nextInt();

        System.out.println("Od kdy:");
        int start = hours(sc.next());

        System.out.println("Do kdy:");
        int end = hours(sc.next());

        makeRes(customer, players, day, court, start, end);
    }
    /**
     * makes reservation with inputted parameters
     */
    public void makeRes(String customer, int players, int day, int court, int start, int end) {
        if (!isRes(day, court, start,end)) {
            Reservation temp = new Reservation(customer, players, day, court, start, end);
            reservationList.add(temp);
            reservedTimes[day-1][court-1].setTimes(start, end,customer);
            System.out.println("Rezervace byla vytvořena");
        }
    }
    /**
     * checks if the specific time is free via input
     */
    public boolean isRes(){
        System.out.println("Den:");
        int day = whichDay(sc.next());
        System.out.println("Kurt:");
        int court = sc.nextInt();
        System.out.println("Od kdy:");
        int start = whichDay(sc.next());
        System.out.println("Do kdy:");
        int end = whichDay(sc.next());
        return isRes(day,court,start,end);
    }
    /**
     * checks if the specific time is free
     */
    public boolean isRes(int day,int court,int start, int end) {
        boolean result = false;
        StringBuilder vypis = new StringBuilder("Kolize v časech: ");
        for(int i=start;i<end;i++){
            if(reservedTimes[day-1][court-1].isReserved(i)){
                vypis.append(hours(i)).append(", ");
                result = true;
            }
        }
        if(result){
            System.out.println(vypis);
        }
        return result;
    }
    /**
     * removes reservation via input
     */
    public void removeReservation(){
        System.out.println("Rezervující: ");
        String customer = sc.next();

        System.out.println("Jaký den: ");
        int day = whichDay(sc.next());

        System.out.println("Kurt: ");
        int court = sc.nextInt();

        System.out.println("Od kdy: ");
        int start = hours(sc.next());

        removeReservation(customer,day,court,start);
    }
    /**
     * removes reservation with inputted parameters
     */
    public void removeReservation(String customer,int day,int court, int start ){
        reservedTimes[day-1][court-1].remTimes(start, customer);
        reservationList.removeIf(temp -> start == temp.getStart() && day == temp.getDay() && customer.equals(temp.getCus()));

        System.out.println("Rezervace byla zrušena");
    }
    /**
     * edits reservation via input
     */
    public void editReservation(){
        System.out.println("Jméno rezervujícího: ");
        String customer = sc.next();
        System.out.println("Jaký den: ");
        int day = whichDay(sc.next());
        System.out.println("Kurt: ");
        int court = sc.nextInt();
        System.out.println("Od kdy:");
        int start = hours(sc.next());
        editReservation(customer,day,court,start);
    }
    /**
     * edits reservation with inputted parameters
     */
    public void editReservation(String customer, int day,int court,int start){
        boolean wasFound = false;
        for(int i = 0;i<reservationList.size();i++){
            Reservation temp = reservationList.get(i);
            if(start == temp.getStart() && day == temp.getDay() && court == temp.getCourt() && customer.equals(temp.getCus())){
                wasFound=true;
                int decision = editMenu();
                switch(decision){
                    case 1 -> temp.editCus();
                    case 2 -> temp.editPla();
                    case 3 -> temp.editDay();
                    case 4 -> temp.editCou();
                    case 5 -> temp.editTime();
                    case 6 -> temp.editRes();
                }
            }
            reservationList.set(i,temp);
            System.out.println("Rezervace byla upravena");
            break;
        }
        if(!wasFound){
            System.out.println("Rezervace nebyla nalezena pro editaci");
        }
    }
    /**
     * prints out choices for editing
     * @return int for switch
     */
    public int editMenu(){
        System.out.println("Zadejte možnost kterou chcete editovat u této rezervace:\n"+
        "1 - pro editaci rezervujícího\n"+
        "2 - pro editaci počtu hráčů\n"+
        "3 - pro editaci dne\n"+
        "4 - pro editaci čísla kurtu\n"+
        "5 - pro editaci času rezervace\n"+
        "6 - pro editaci celé rezervace");
        return sc.nextInt();
    }
    /**
     * prints out amount that needs to be paid via input
     */
    public void endReservation(){
        System.out.println("Rezervující:");
        String customer = sc.next();
        System.out.println("Den:");
        int day = whichDay(sc.next());
        System.out.println("Od kdy:");
        int start = hours(sc.next());
        endReservation(customer,day,start);
    }
    /**
     * prints out amount that needs to be paid with inputted parameters
     */
    public void endReservation(String customer,int day,int start){
        String vypis="Nebyla nalezena rezervace";
        for(Reservation temp :reservationList){
            if(temp.getCus().equals(customer)&&day==temp.getDay()&&start==temp.getStart()){
                vypis=(cena*(temp.getEnd()+temp.getStart()))/temp.getPla()+" kč na osobu; celková částka: "+cena*(temp.getEnd()+temp.getStart());
                break;
            }
        }
        System.out.println(vypis);;
    }
    /**
     * prints out reservations of specific customer for this week via input
     */
    public void allCustomerReservationsForWeek(){
        System.out.println("Zadejte jméno rezervujícího:");
        String customer = sc.next();
        allCustomerReservationsForWeek(customer);
    }
    /**
     * prints out reservations of specific customer for this week with inputted parameters
     */
    public void allCustomerReservationsForWeek(String customer) {
        boolean vypsalo = false;
        for(Reservation temp:reservationList){
            String vypis = "";
            if(customer.equals(temp.getCus())){
                vypsalo=true;
                vypis="Kurt číslo: "+temp.getCourt()+"; v: "+whichDay(temp.getDay())+"; od: "+hours(temp.getStart())+"do: "+hours(temp.getEnd());
            }
            System.out.println(vypis);
        }
        if(!vypsalo){
            System.out.println("Zákazník neměl žádné rezervace");
        }
    }
    /**
     * prints out reservations of specific customer for specific day in this week via input
     */
    public void allCustomerReservationsForDay(){
        System.out.println("Zadejte jméno rezervujícího:");
        String customer = sc.next();
        System.out.println("Zadejte den:");
        int day = whichDay(sc.next());
        allCustomerReservationsForDay(customer,day);

    }
    /**
     * prints out reservations of specific customer for specific day in this week with inputted parameters
     */
    public void allCustomerReservationsForDay(String customer,int day){
        boolean vypsalo = false;
        for(Reservation temp:reservationList){
            String vypis = "";
            if(customer.equals(temp.getCus()) && temp.getDay()==day){
                vypsalo=true;
                vypis="Kurt číslo: "+temp.getCourt()+"; od: "+hours(temp.getStart())+"do: "+hours(temp.getEnd());
            }
            System.out.println(vypis);
        }
        if(!vypsalo){
            System.out.println("Zákazník neměl žádné rezervace v tento den");
        }
    }
    /**
     * prints out reservations of specific customer for today via input
     */
    public void allCustomerReservationsForToday(){
        System.out.println("Rezervující");
        String customer = sc.next();
        allCustomerReservationsForToday(customer);
    }
    /**
     * prints out reservations of specific customer for today with inputted parameters
     */
    public void allCustomerReservationsForToday(String customer){
        boolean vypsalo = false;
        for(Reservation temp:reservationList){
            String vypis = "";
            LocalDateTime day = LocalDateTime.now();
            if(customer.equals(temp.getCus()) && temp.getDay()==day.getDayOfWeek().getValue()){
                vypsalo=true;
                vypis="Kurt číslo: "+temp.getCourt()+"; od: "+hours(temp.getStart())+"do: "+hours(temp.getEnd());
            }
            System.out.println(vypis);
        }
        if(!vypsalo){
            System.out.println("Zákazník nemá dnes žádnou rezervaci");
        }
    }
    /**
     * prints out what time is free today
     */
    public void whatIsFreeToday(){
        LocalDateTime date = LocalDateTime.now();
        int day = date.getDayOfWeek().getValue();
        whatIsFreeDay(day);
    }
    /**
     * prints out what time is free on specific day via input
     */
    public void whatIsFreeDay(){
        System.out.println("Den:");
        int day = whichDay(sc.next());
        whatIsFreeDay(day);
    }
    /**
     * prints out what time is free on specific day, with inputted parameters
     */
    public void whatIsFreeDay(int day){
        String vypis;
        for(int i = 0 ; i< reservedTimes[day-1].length;i++){
            vypis = "V +"+whichDay(day)+"je volno na kurtu číslo: "+i+1+"v tyto časy: ";
            for(int j =0;j<24;j++){
                if (!reservedTimes[day-1][i].isReserved(j)){
                    vypis+=whichDay(j)+", ";
                }
            }
            System.out.println(vypis);
        }
    }
    /**
     * prints out what time is free in this week
     */
    public void whatIsFreeWeek(){
        String vypis;
        for(int i = 0; i<reservedTimes.length;i++){
            for(int j =0;j<reservedTimes[i].length;j++){
                vypis = "V +"+whichDay(i)+"je volno na kurtu číslo: "+j+1+"v tyto časy: ";
                for (int k = 0;k<24;k++){
                    if (!reservedTimes[i][j].isReserved(k)){
                        vypis+=whichDay(k)+", ";
                    }
                }
            }
        }
    }
    /**
     * takes day in String form and translates it to int form
     * (for example param is monday; return is 1)
     */
    public static int whichDay(String a) {
        return switch (a) {
            case "pondeli", "pondělí", "Pondělí", "Pondeli", "1","monday"-> 1;
            case "utery", "úterý", "Úterý", "Utery", "2","tuesday" -> 2;
            case "streda", "středa", "Středa", "Streda", "3","wednesday" -> 3;
            case "ctvrtek", "čtvrtek", "Ctvrtek", "Čtvrtek", "4","thursday" -> 4;
            case "patek", "pátek", "Pátek", "Patek", "5","friday" ->5;
            case "sobota", "Sobota", "6", "saturday" -> 6;
            case "nedele", "neděle", "Neděle", "Nedele", "7", "sunday" ->7;
            default -> throw new IllegalStateException("Neočekávaná hodnota: " + a);
        };
    }
    /**
     * takes day in int form and translates it to String form
     * (for example param is 1; return is ponděli)
     */
    public static String whichDay(int a) {
        return switch (a) {
            case 1 -> "pondeli";
            case 2 -> "utery";
            case 3 -> "streda";
            case 4 -> "ctvrtek";
            case 5 -> "patek";
            case 6 -> "sobota";
            case 7 -> "nedele";
            default -> throw new IllegalStateException("Neočekávaná hodnota: " + a);
        };
    }
    /**
     * takes time in String form and translates it to int form
     * (for example input is 14:00; return is 14)
     */
    public static int hours(String a) {
        String[] parts = a.split(":");
        return Integer.parseInt(parts[0]);
    }
    /**
     * takes time in int form and translates it to String form
     * (for example input is 14; return is 14:00)
     */
    public static String hours(int a) {
        return a + ":00";
    }
    /**
     * loads data from file
     * @param param - number of week
     */
    public void loadFromFile(int param) throws IOException {
        Gson gson = new Gson();
        this.reservationList = gson.fromJson(new FileReader("./2223alg2-semestralproject-Granc3k/reservationSystemTenis/data/save_"+param+".json"),  new TypeToken<ArrayList<Reservation>>(){}.getType());
        loadTimes();
    }
    /**
     * automatic load of 2D array of objects
     */
    private void loadTimes(){
        for(Reservation temp:reservationList){
                reservedTimes[temp.getDay()-1][temp.getCourt()-1].setTimes(temp.getStart(),temp.getEnd(), temp.getCus());
        }
    }
    /**
     * saves data from reservationList to .json file
     * @param param - number of week
     */
    public void saveToFile(int param) throws IOException {
        Gson gson = new Gson();
        String serialized = gson.toJson(reservationList);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./2223alg2-semestralproject-Granc3k/reservationSystemTenis/data/save_"+param+".json"))){
            writer.write(serialized, 0, serialized.length());
        }
    }
}
