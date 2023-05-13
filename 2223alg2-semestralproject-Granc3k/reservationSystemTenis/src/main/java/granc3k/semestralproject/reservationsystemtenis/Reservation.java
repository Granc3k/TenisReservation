package granc3k.semestralproject.reservationsystemtenis;

import java.util.Scanner;

public class Reservation {
    public static Scanner sc = new Scanner(System.in);
    private String customer;
    private int players;
    private int day;
    private int court;
    private int startHour;
    private int endHour;
    public Reservation(String customer, int players, int day, int court, int startHour, int endHour) {
        this.customer = customer;
        this.players = players;
        this.day = day;
        this.court = court;
        this.startHour = startHour;
        this.endHour = endHour;
    }
    public void editRes() {
        editCus();
        editPla();
        editDay();
        editCou();
        editTime();
    }
    public void editPla() {
        System.out.println("Zadejte nový počet hráčů:");
        this.players = sc.nextInt();
    }
    public void editCus() {
        System.out.println("Zadejte nového rezervujícího:");
        this.customer = sc.next();
    }
    public void editDay() {
        System.out.println("Zadejte nový den:");
        this.day = sc.nextInt();
    }
    public void editCou() {
        System.out.println("Zadejte nové číslo kurtu:");
        this.court = sc.nextInt();
    }
    public void editTime(){
        System.out.println("Zadejte nový začáteční čas:");
        this.startHour=sc.nextInt();
        System.out.println("Zadejte nový konečný čas:");
        this.endHour=sc.nextInt();
    }
    public int getPla() {
        return players;
    }
    public int getDay() {
        return day;
    }
    public int getCourt() {
        return court;
    }
    public int getStart() {
        return startHour;
    }
    public int getEnd() {
      return endHour;
    }
    public String getCus() {
        return customer;
    }
}
