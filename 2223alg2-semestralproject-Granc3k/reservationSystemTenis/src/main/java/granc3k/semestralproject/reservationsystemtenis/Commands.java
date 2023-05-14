package granc3k.semestralproject.reservationsystemtenis;

// data in commands hierarchically sorted: customer, players, day, court, start, end,

public class Commands {
    /**
     * makes reservation
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void res(Reservations a, String[] b){
        if(b.length>1) {
            if (b.length == 7) {
                //via command without necessary input without necessary input
                String customer = b[1];
                int players = Integer.parseInt(b[2]);
                int day = Reservations.whichDay(b[3]);
                int court = Integer.parseInt(b[4]);
                int start = Reservations.hours(b[5]);
                int end = Reservations.hours(b[6]);
                a.makeRes(customer, players, day, court, start, end);
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.makeRes();
            }
        }else{
            //via input
            a.makeRes();
        }
    }
    /**
     * edits reservation
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void editRes(Reservations a, String[] b) {
        if (b.length > 1) {
            if (b.length == 5) {
                //via command without necessary input
                a.editReservation(b[1],Reservations.whichDay(b[2]),Integer.parseInt(b[3]),Reservations.hours(b[4]));
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.editReservation();
            }
        }else{
            //via input
            a.editReservation();
        }
    }
    /**
     * removes reservation
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void remRes(Reservations a, String[] b){
        if (b.length > 1) {
            if (b.length == 5) {
                //via command without necessary input
                a.removeReservation(b[1],Reservations.whichDay(b[2]), Integer.parseInt(b[3]),Reservations.hours(b[4]));
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.removeReservation();
            }
        }else{
            //via input
            a.removeReservation();
        }
    }
    /**
     * checks if the time is reserved
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void isRes(Reservations a, String[] b){
        if (b.length > 1) {
            if (b.length == 5) {
                //via command without necessary input
                int day = Reservations.whichDay(b[1]);
                int court = Integer.parseInt(b[2]);
                int start = Reservations.hours(b[3]);
                int end = Reservations.hours(b[4]);
                ;
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                ;
            }
        }else{
            //via input
            ;
        }
    }
    //TODO

    /**
     * ends reservation and prints out amount of money to pay
     * @param a is a parameter of type object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void endRes(Reservations a, String[] b){
        if (b.length > 1) {
            if (b.length == 4) {
                //via command without necessary input
                a.endReservation(b[1],Reservations.whichDay(b[2]),Reservations.hours(b[3]));
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.endReservation();
            }
        }else{
            //via input
            a.endReservation();
        }
    }
    /**
     * lists customer reservations for week
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void listForWeek(Reservations a,String[] b){
        if (b.length > 1) {
            if (b.length == 3) {
                //via command without necessary input
                a.allCustomerReservationsForWeek(b[2]);
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.allCustomerReservationsForWeek();
            }
        }else{
            //via input
            a.allCustomerReservationsForWeek();
        }
    }
    /**
     * lists customer reservations for specific day
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void listForDay(Reservations a, String[] b) {
        if (b.length > 1) {
            if (b.length == 4) {
                //via command without necessary input
                a.allCustomerReservationsForDay(b[2], Reservations.whichDay(b[3]));
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.allCustomerReservationsForDay();
            }
        }else{
            //via input
            a.allCustomerReservationsForDay();
        }
    }
    /**
     * lists customer reservations for this day via java date API
     * @param a is a parameter of object that you work with
     * @param b is a param for parsed String used for commands
     */
    public static void listForToday(Reservations a, String[] b){
        if (b.length > 1) {
            if (b.length == 3) {
                //via command without necessary input
                a.allCustomerReservationsForToday(b[2]);
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                a.allCustomerReservationsForToday();
            }
        }else{
            //via input
            a.allCustomerReservationsForToday();
        }
    }
    /**
     * help command
     * prints out necessary information to every command
     */
    public static void help() {
        System.out.println("Tvoření rezervace:\n"+
        "\n"+
        "res/1 -- vytváří rezervaci, uživatel doplní parametry\n"+
        "res/1 [rezervující(ve tvaru jmeno.prijmeni)] [počet hráčů] [den (textem)] [číslo kurtu] [od kdy (ve formátu xx:00)][do kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Editace rezervace\n"+
        "\n"+
        "edit/2 -- edituje stávající rezervaci, uživatel doplní parametry\n"+
        "edit/2 [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [číslo kurtu] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Odstranění rezervace:\n"+
        "\n"+
        "rem/3 -- odstraní rezervaci, uživatel doplní parametry\n"+
        "rem/3 [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Výpis rezervací:\n"+
        "\n"+
        "list/4 dnes/today/t -- vypíše rezervace zákazníka, uživatel doplní parametry\n"+
        "list/4 dnes/today/t [rezervující(ve tvaru jmeno.prijmeni)]\n"+
        "list/4 tyden/week/w -- vypíše rezervace zákazníka na týden, uživatel doplní parametry\n"+
        "list/4 tyden/week/w [rezervující(ve tvaru jmeno.prijmeni)]\n"+
        "list/4 den/day/d -- vypíše rezervace zákazníka v určitý den, uživatel doplní parametry\n"+
        "list/4 den/day/d [rezervující(ve tvaru jmeno.prijmeni)][den (textem)]\n"+
        "\n"+
        "Je rezervován tento termín:\n"+
        "teprve se doladí\n"+
        "\n"+
        "Ukončení rezervace:\n"+
        "endres/5 -- spočítá kolik je třeba zaplatit za daný kurt, uživatel doplní parametry\n"+
        "endres/5 [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Ukončení programu:\n"+
        "exit -- ukončí program a uloží data do souborů");

    }
    //TODO
}
