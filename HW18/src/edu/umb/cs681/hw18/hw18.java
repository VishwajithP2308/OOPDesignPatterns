package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class hw18 {
    public static void main(String[] args) {
        Path path=Paths.get("BosNeighborhoodData.csv");
        List<List<String>> matrix = null;
        try(Stream<String>lines=Files.lines(path)){
            matrix=lines
                    .map(line->{
                        return Stream.of(line.split(",")).skip(1)
                                .map(value->value.substring(0,value.length()))
                                .collect(Collectors.toList());})
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.getMessage();
        }

        matrix.remove(0);
        matrix.remove(0);
        System.out.println("The Link for the dataset i've used is below : ");
        System.out.println("https://data.boston.gov/dataset/2020-census-for-boston/resource/5800a0a2-6acd-41a3-9fe0-1bf7b038750d");
        System.out.println();
        System.out.println("First Data Processing ");
        System.out.println("I'm finding the highest occupied area, least occupied area,highest vacant area, least vacant area respectively");
        double highestOccupied = matrix.stream().parallel()
                .map(r -> Double.parseDouble(r.get(30)))
                .max(Double::compare).get();
        double leastOccupied = matrix.stream().parallel()
                .map(r -> Double.parseDouble(r.get(30))).
                min(Double::compare).get();
        double highestVacant = matrix.stream().parallel()
                .map(r -> Double.parseDouble(r.get(31))).
                max(Double::compare).get();
        double leastVacant = matrix.stream().parallel()
                .map(r -> Double.parseDouble(r.get(31))).
                min(Double::compare).get();

        System.out.println("Highest Occupied area : " +highestOccupied);
        System.out.println("Least Occupied area : "+leastOccupied);
        System.out.println("Highest Vacant area : "+highestVacant);
        System.out.println("Least Vacant area : "+leastVacant);
        System.out.println();
        System.out.println("Second Data Processing ");
        System.out.println("I'm finding out the area with highest household where the military quarters are present");
        Map<Boolean, List<List<String>>> Partition = matrix.stream().parallel().collect(Collectors.partitioningBy(r -> {
            double militaryQuarters = Double.parseDouble(r.get(27));
            return militaryQuarters > 0;
        }));
        List<List<String>> hasMilitaryQuarters = Partition.get(true);
        double highestHousehold = hasMilitaryQuarters.stream().parallel()
                .map(r -> Double.parseDouble(r.get(32)))
                .max(Double::compare).get();

        System.out.println(highestHousehold);
        System.out.println("Therefore in the given dataset, 2 rows have military quarters with 30 and 116 respectively, ");
        System.out.println("Amongst these 2 rows, highest household is 2.128302732.");
        System.out.println();
        System.out.println("Thirs data processing, i would like to relate other institutional facilities and college/University student housing columns");
        System.out.println("Here i would like to find out the average of college/University student housing where institutional facilities are present.");
        Map<Boolean, List<List<String>>> PartitionInstitutionalFacilities = matrix.stream().parallel().collect(Collectors.partitioningBy(r -> {
            double InstitutionalFacilities = Double.parseDouble(r.get(24));
            return InstitutionalFacilities  > 0;
        }));
        List<List<String>> hasInstitutionalFacilities = PartitionInstitutionalFacilities.get(true);
        double sum = hasInstitutionalFacilities.stream().parallel()
                .map(row -> Double.parseDouble(row.get(26)))
                .reduce(0.0, Double::sum);
        long size = hasInstitutionalFacilities.size();
        if(size > 0){
            double average = sum/size;
            System.out.println("Average college/University housing is: "+average);
        }

    }
}


