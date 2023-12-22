package edu.umb.cs681.hw13;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Task {
    private String name;
    private Date date;
    private int priority;

    public Task(String name, String date, int priority) throws ParseException {
        this.name = name;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", priority=" + priority +
                '}';
    }
}