package granc3k.semestralproject.reservationsystemtenis.utils;

import java.util.Scanner;

public class Reservation {
    public static Scanner sc = new Scanner(System.in);
    private String customer;
    private int players;
    private int day;
    private int court;
    private int startHour;
    private int endHour;

    /**
     * constructor for object
     * @param customer - name of customer
     * @param players - number of players on court
     * @param day - which day in week (in int form)
     * @param court - number of the court
     * @param startHour - start of the reservation(in shortened form - for example: 14 == 14:00)
     * @param endHour - end of the reservation (same form as startHour)
     */
    public Reservation(String customer, int players, int day, int court, int startHour, int endHour) {
        this.customer = customer;
        this.players = players;
        this.day = day;
        this.court = court;
        this.startHour = startHour;
        this.endHour = endHour;
    }
    /**
     * edits whole reservation via input
     */
    public void editRes() {
        editCus();
        editPla();
        editDay();
        editCou();
        editTime();
    }
    /**
     * edits only player via input
     */
    public void editPla() {
        System.out.println("Zadejte nový počet hráčů:");
        this.players = sc.nextInt();
    }
    /**
     * edits name of the customer via input
     */
    public void editCus() {
        System.out.println("Zadejte nového rezervujícího:");
        this.customer = sc.next();
    }
    /**
     * edits day number via input
     */
    public void editDay() {
        System.out.println("Zadejte nový den:");
        this.day = sc.nextInt();
    }
    /**
     * edits court number via input
     */
    public void editCou() {
        System.out.println("Zadejte nové číslo kurtu:");
        this.court = sc.nextInt();
    }
    /**
     * edits startHour and endHour via input
     */
    public void editTime(){
        System.out.println("Zadejte nový začáteční čas:");
        this.startHour=sc.nextInt();
        System.out.println("Zadejte nový konečný čas:");
        this.endHour=sc.nextInt();
    }
    /**
     * getter for number of players
     */
    public int getPla() {
        return players;
    }
    /**
     * getter for day number
     */
    public int getDay() {
        return day;
    }
    /**
     * getter for court number
     */
    public int getCourt() {
        return court;
    }
    /**
     * getter for startHour
     */
    public int getStart() {
        return startHour;
    }
    /**
     * getter for endHour
     */
    public int getEnd() {
      return endHour;
    }
    /**
     * getter for customer
     */
    public String getCus() {
        return customer;
    }
}
