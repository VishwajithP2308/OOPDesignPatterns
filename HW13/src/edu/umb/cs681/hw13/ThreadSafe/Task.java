package edu.umb.cs681.hw13.ThreadSafe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Task {
    private String name;
    private Date date;
    private int priority;

    private ReentrantLock lock=new ReentrantLock();
    public Task(String name, String date, int priority) throws ParseException {
        this.name = name;
        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.priority = priority;
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        }
        finally {
            lock.unlock();
        }
    }

    public Date getDate() {
        lock.lock();
        try {
            return date;
        }
        finally{
            lock.unlock();
        }
    }

    public int getPriority() {
        lock.lock();
        try {
            return priority;
        }
        finally{
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", date=" + date +
                    ", priority=" + priority +
                    '}';
        }
        finally{
            lock.unlock();
        }
    }
}