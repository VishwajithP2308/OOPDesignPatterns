package edu.umb.cs681.hw06;


public class HW6 {
public static void main(String[] args){
    DataProcessing1 dataProcessing1 = new DataProcessing1();
    DataProcessing2 dataProcessing2 = new DataProcessing2();
    DataProcessing3 dataProcessing3 = new DataProcessing3();
    Thread thread1 = new Thread(dataProcessing1);
    Thread thread2 = new Thread(dataProcessing2);
    Thread thread3 = new Thread(dataProcessing3);

    thread1.start();
    thread2.start();
    thread3.start();
    try {
        thread1.join();
        thread2.join();
        thread3.join();
    }catch (Exception e){
        e.getMessage();
    }
    System.out.println("Result of first data processing: "+ dataProcessing1.getResult());
    System.out.println("Result of second data processing: "+dataProcessing2.getResult());
    System.out.println("Result of third data processing: "+dataProcessing3.getResult());

}
}
