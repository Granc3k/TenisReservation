package granc3k.semestralproject.reservationsystemtenis.app;

// data in commands hierarchically sorted: customer, players, day, court, start, end,

import granc3k.semestralproject.reservationsystemtenis.app.Reservations;

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
        boolean decission;
        if (b.length > 1) {
            if (b.length == 5) {
                //via command without necessary input
                int day = Reservations.whichDay(b[1]);
                int court = Integer.parseInt(b[2]);
                int start = Reservations.hours(b[3]);
                int end = Reservations.hours(b[4]);
                decission=a.isRes(day,court,start,end);
            } else {
                //via input
                System.out.println("Příkaz byl zadán špatně!\n"+ "Spouštím verzi pro doplnění parametrů");
                decission=!a.isRes();
            }
        }else{
            //via input
            decission=!a.isRes();
        }
        if(decission){
            System.out.println("V tento čas je již udělaná rezervace");
        }else{
            System.out.println("Tento čas je volný");
        }
    }
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
        a.sortReservationsByCustomer();
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
        a.sortReservationsByCustomer();
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
        a.sortReservationsByCustomer();
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
    public static void whatIsFreeWeek(Reservations a, String[] b){
        a.whatIsFreeWeek();
    }
    public static void whatIsFreeDay(Reservations a, String[] b) {
        if (b.length > 1) {
            if (b.length == 2) {
                a.whatIsFreeDay(Reservations.hours(b[1]));
            }
            //via input
            System.out.println("Příkaz byl zadán špatně!\n" + "Spouštím verzi pro doplnění parametrů");
            a.whatIsFreeDay();
        } else {
            //via input
            a.whatIsFreeDay();
        }
    }
    public static void whatIsFreeToday(Reservations a){
        a.whatIsFreeToday();
    }

    public static void listAll(Reservations a){
        a.sortReservationsByCustomer();
        a.listAll();
    }
    public static void listForDate(){
        Reservations temp = new Reservations();
        System.out.println("Zadejte datum");
        temp.findReservationFileByDateJson();
    }



    /**
     * help command
     * prints out necessary information to every command
     */
    public static void help() {
        System.out.println("\n"+
        "Tvoření rezervace:\n"+
        "1/res -- vytváří rezervaci, uživatel doplní parametry\n"+
        "1/res [rezervující(ve tvaru jmeno.prijmeni)] [počet hráčů] [den (textem)] [číslo kurtu] [od kdy (ve formátu xx:00)][do kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Editace rezervace:\n"+
        "2/edit -- edituje stávající rezervaci, uživatel doplní parametry\n"+
        "2/edit [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [číslo kurtu] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Odstranění rezervace:\n"+
        "3/rem -- odstraní rezervaci, uživatel doplní parametry\n"+
        "3/rem [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Výpis rezervací:\n"+
        "4/list + [dále doplněný parametr] -- vypíše rezervace zákazníka v daném časovém úseku, uživatel doplní parametry\n"+
        "\n"+
        "Je rezervován tento termín:\n"+
        "5/isres -- vypíše zda je místo volné v zadaném čase, či kolizi v určitých časech, uživatel doplní parametry\n"+
        "5/isres [den (textem)] [číslo kurtu] [od kdy(ve formátu xx:00)][do kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Ukončení rezervace:\n"+
        "6/endres -- spočítá kolik je třeba zaplatit za daný kurt, uživatel doplní parametry\n"+
        "6/endres [rezervující(ve tvaru jmeno.prijmeni)] [den (textem)] [od kdy (ve formátu xx:00)]\n"+
        "\n"+
        "Vypsání, co je volné:\n"+
        "7/free + [dále doplněný parametr] -- vypíše volné časy v daném časovém úseku\n"+
        "\n"+
        "Přepnutí týdne:\n"+
        "8/switch -- přepne z aktuálního týdne(current) na následující týden(next) a naopak\n"+
        "\n"+
        "9/save -- uložení do souborů\n"+
        "\n"+
        "Ukončení programu:\n"+
        "exit -- ukončí program a uloží data do souborů\n"+
        "\n"+
        "debug -- pro vypisování tracu chyby\n");

    }
}
