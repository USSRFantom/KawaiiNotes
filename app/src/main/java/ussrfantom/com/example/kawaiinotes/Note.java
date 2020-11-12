package ussrfantom.com.example.kawaiinotes;

public class Note {
    private String title;  //заголовок
    private String description; //описание
    private String dayOfWeek; //день недели
    private int priority; //приоритет

    //конструктор
    public Note(String title, String description, String dayOfWeek, int priority) {
        this.title = title;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getPriority() {
        return priority;
    }
}
