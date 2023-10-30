package edu.umb.cs681.hw06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing3 extends Thread{
    double result = 0;
    @Override
    public void run(){
        Path path= Paths.get("BosNeighborhoodData.csv");
        List<List<String>> matrix = null;
        try(Stream<String> lines= Files.lines(path)){
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
        Map<Boolean, List<List<String>>> PartitionInstitutionalFacilities = matrix.stream().collect(Collectors.partitioningBy(r -> {
            double InstitutionalFacilities = Double.parseDouble(r.get(24));
            return InstitutionalFacilities  > 0;
        }));
        List<List<String>> hasInstitutionalFacilities = PartitionInstitutionalFacilities.get(true);
        double sum = hasInstitutionalFacilities.stream()
                .map(row -> Double.parseDouble(row.get(26)))
                .reduce(0.0, Double::sum);
        long size = hasInstitutionalFacilities.size();
        if(size > 0){
            double average = sum/size;
            result = average;
        }
    }

    public double getResult() {
        return result;
    }
}
