package edu.umb.cs681.hw06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing2 extends Thread {
    double result = 0.0;
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
        Map<Boolean, List<List<String>>> Partition = matrix.stream().collect(Collectors.partitioningBy(r -> {
            double militaryQuarters = Double.parseDouble(r.get(27));
            return militaryQuarters > 0;
        }));
        List<List<String>> hasMilitaryQuarters = Partition.get(true);
        double highestHousehold = hasMilitaryQuarters.stream()
                .map(r -> Double.parseDouble(r.get(32)))
                .max(Double::compare).get();
        result = highestHousehold;
    }

    public double getResult() {
        return result;
    }
}
