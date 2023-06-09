package granc3k.semestralproject.reservationsystemtenis.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public enum WeekDay {
    MONDAY(1, "pondeli", "pondělí", "Pondělí", "Pondeli", "1", "monday", "Monday", "MONDAY"),
    TUESDAY(2, "utery", "úterý", "Úterý", "Utery", "2", "tuesday", "Tuesday", "TUESDAY"),
    WEDNESDAY(3, "streda", "středa", "Středa", "Streda", "3", "wednesday", "Wednesday", "WEDNESDAY"),
    THURSDAY(4, "ctvrtek", "čtvrtek", "Ctvrtek", "Čtvrtek", "4", "thursday", "Thursday", "THURSDAY"),
    FRIDAY(5, "patek", "pátek", "Pátek", "Patek", "5", "friday", "Friday", "FRIDAY"),
    SATURDAY(6, "sobota", "Sobota", "6", "saturday", "Saturday", "SATURDAY"),
    SUNDAY(7, "nedele", "neděle", "Neděle", "Nedele", "7", "sunday", "Sunday", "SUNDAY");
    private List<String> aliases;
    private int id;

    WeekDay(int id, String... aliases) {
        this.id = id;
        this.aliases = List.of(aliases);
    }

    public List<String> getAliases() {
        return aliases;
    }

    public int getId() {
        return id;
    }
}
