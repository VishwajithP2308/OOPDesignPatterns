package edu.umb.cs681.hw06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing1 extends Thread {
    List<Double> result = null;
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
        double highestOccupied = matrix.stream()
                .map(r -> Double.parseDouble(r.get(30)))
                .max(Double::compare).get();
        double leastOccupied = matrix.stream()
                .map(r -> Double.parseDouble(r.get(30)))
                .min(Double::compare).get();
        double highestVacant = matrix.stream()
                .map(r -> Double.parseDouble(r.get(31)))
                .max(Double::compare).get();
        double leastVacant = matrix.stream()
                .map(r -> Double.parseDouble(r.get(31)))
                .min(Double::compare).get();
        result = List.of(highestOccupied, leastOccupied, highestVacant, leastVacant);
    }

    public List<Double> getResult() {
        return result;
    }
}
