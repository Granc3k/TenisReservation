package granc3k.semestralproject.reservationsystemtenis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Reservations {
    static final int cena = 300;
    private ArrayList<Reservation> reservationList;
    private final Times[][] reservedTimes;
    public static Scanner sc = new Scanner(System.in);
    public Reservations() {
        this.reservationList = new ArrayList<Reservation>();
        this.reservedTimes = new Times[7][6];//7 days in week and 6 courts
        fillTimes();
    }
    private void fillTimes(){
        for (int i = 0; i < reservedTimes.length; i++) {
            for(int j=0;j<reservedTimes[i].length;j++){
                reservedTimes[i][j]=new Times();
            }
        }
    }
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
    public void makeRes(String customer, int players, int day, int court, int start, int end) {
        if (isFree(day, court, start,end)) {
            Reservation temp = new Reservation(customer, players, day, court, start, end);
            reservationList.add(temp);
            reservedTimes[day-1][court-1].setTimes(start, end,customer);
            System.out.println("Rezervace byla vytvořena");
        }
    }
    public boolean isFree(int day,int court,int start, int end) {
        boolean result = true;
        StringBuilder vypis = new StringBuilder("Kolize v časech: ");
        for(int i=start;i<end;i++){
            if(reservedTimes[day-1][court-1].isReserved(i)){
                vypis.append(hours(i)).append(", ");
                result = false;
            }
        }
        if(!result){
            System.out.println(vypis);
        }
        return result;
    }
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
    public void removeReservation(String customer,int day,int court, int start ){
        reservedTimes[day-1][court-1].remTimes(start, customer);
        reservationList.removeIf(temp -> start == temp.getStart() && day == temp.getDay() && customer.equals(temp.getCus()));

        System.out.println("Rezervace byla zrušena");
    }
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
    public void endReservation(){
        System.out.println("Rezervující:");
        String customer = sc.next();
        System.out.println("Den:");
        int day = whichDay(sc.next());
        System.out.println("Od kdy:");
        int start = hours(sc.next());
        endReservation(customer,day,start);
    }
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
    public void allCustomerReservationsForWeek(){
        System.out.println("Zadejte jméno rezervujícího:");
        String customer = sc.next();
        allCustomerReservationsForWeek(customer);
    }
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
    public void allCustomerReservationsForDay(){
        System.out.println("Zadejte jméno rezervujícího:");
        String customer = sc.next();
        System.out.println("Zadejte den:");
        int day = whichDay(sc.next());
        allCustomerReservationsForDay(customer,day);

    }
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
    public void allCustomerReservationsForToday(){
        System.out.println("Rezervující");
        String customer = sc.next();
        allCustomerReservationsForToday(customer);
    }
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
    public void whatIsFreeToday(){

    }
    public void whatIsFreeDay(int day){
        for(int i =0;i<6;i++){
            //TODO
        }
    }
    public void whatIsFree(){
        //TODO
    }
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
    public static int hours(String a) {
        String[] parts = a.split(":");
        return Integer.parseInt(parts[0]);
    }
    public static String hours(int a) {
        return a + ":00";
    }
    public void loadFromFile(int param) throws IOException {
        Gson gson = new Gson();
        this.reservationList = gson.fromJson(new FileReader("./data/"+param+".json"),  new TypeToken<ArrayList<Reservation>>(){}.getType());
        loadTimes();
    }
    private void loadTimes(){
        for(Reservation temp:reservationList){
                reservedTimes[temp.getDay()-1][temp.getCourt()-1].setTimes(temp.getStart(),temp.getEnd(), temp.getCus());
        }
    }
    public void addReservation(Reservation temp){
        reservationList.add(temp);
        reservedTimes[temp.getDay()-1][temp.getCourt()-1].setTimes(temp.getStart(), temp.getEnd(), temp.getCus());
    }
    public void saveToFile(int param) throws IOException {
        //new File("./data/").mkdirs();
        Gson gson = new Gson();
        String serialized = gson.toJson(reservationList);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./data/save_"+param+".json"))){
            writer.write(serialized, 0, serialized.length());
        }
    }
}
