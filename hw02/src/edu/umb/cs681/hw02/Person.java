package edu.umb.cs681.hw02;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Person {
    private String firstName, lastName;
    private LocalDate dateofBirth;
    private LinkedList<dose> dose = new LinkedList<>();

    public Person(String firstName, String lastName, LocalDate dateofBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
    }

    public ageCat getAgeCat() {
        int age = getAge();
        if (age > 65) return ageCat.OLD;
        else if (age > 35) return ageCat.MID;
        else return ageCat.YOUNG;
    }

    public int getVacCount() {
        return dose.size();
    }
    public int getAge() {
        var age = LocalDate.now().getYear() - dateofBirth.getYear();
        return age;
    }

    public LinkedList<dose> getDoses() {
        return dose;
    }



    public static void main(String[] args) {
        String[] vaccineTypes = {"Moderna", "Pfizer", "AstraZeneca", "Sinopharm"};
        String[] locations = {"Dallas", "NYC", "Miami", "San Francisco"};
        Random randomGen = new Random();

        List<Person> peopleList = new LinkedList<>();

        for (int i = 0; i < 1100; i++) {
            String fname = "Name" + randomGen.nextInt(500);
            String lname = "Surname" + randomGen.nextInt(500);
            LocalDate birthDate = LocalDate.of(1935 + randomGen.nextInt(86), randomGen.nextInt(12) + 1, randomGen.nextInt(28) + 1);

            Person person = new Person(fname, lname, birthDate);

            int vaccinationCount = randomGen.nextInt(5);

            for (int j = 0; j < vaccinationCount; j++) {
                String vaccineName = vaccineTypes[randomGen.nextInt(vaccineTypes.length)];
                String vaccinationCity = locations[randomGen.nextInt(locations.length)];
                dose shot = new dose(vaccineName, "Lot" + randomGen.nextInt(1000), LocalDate.now(), vaccinationCity);
                person.getDoses().add(shot);
            }

            peopleList.add(person);
        }

        Map<ageCat, Long> vaccinationCountsByAge = peopleList
                .stream()
                .filter(p -> p.getVacCount() >= 3)
                .collect(Collectors.groupingBy(person -> person.getAgeCat(), Collectors.counting()));

        System.out.println("Vaccination count by age group: " + vaccinationCountsByAge);

        Map<ageCat, Double> avgVaccinationsPerAgeGroup = peopleList
                .stream()
                .collect(Collectors.groupingBy(person -> person.getAgeCat(), Collectors.averagingInt(person -> person.getVacCount())));
        System.out.println("Avg vaccinations per age group: " + avgVaccinationsPerAgeGroup);

        Map<Boolean, Double> avgAgePartitioned = peopleList
                .stream()
                .collect(Collectors.partitioningBy(person -> person.getVacCount() == 0, Collectors.averagingInt(person -> person.getAge())));
        System.out.println("Mean age of non-vaccinated: " + avgAgePartitioned.get(true));

    }
}
