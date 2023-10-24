package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;


public class Distance {
    public static double get(List<Double> p1, List<Double> p2) {
        return Distance.get(p1, p2, new Euclidean());
    }

    public static double get(List<Double> p1, List<Double> p2, DistanceMetric metric) {
        return metric.distance(p1, p2);
    }

    public static List<List<Double>> matrix(List<List<Double>> points) {
        return Distance.matrix(points, new Euclidean());
    };


        public static List<List<Double>> matrix(List<List<Double>> points, DistanceMetric metric) {
            int numOfPoints = points.size();
            return IntStream.range(0, numOfPoints).parallel()
                    .mapToObj(i -> {
                List<Double> row = new ArrayList<>(Collections.nCopies(numOfPoints, 0.0));
                List<Double> current = points.get(i);
                IntStream.range(0, numOfPoints).forEach(j -> {
                    List<Double> peer = points.get(j);
                    double distance = Distance.get(current, peer, metric);
                    row.set(j, distance);
                });
                return row;
            }).collect(Collectors.toList());
        }


public static void main(String[] args) {

            Random generateRandom = new Random();

            List<List<Double>> points = IntStream.range(0, 1000)
                    .mapToObj(i -> generateRandom.doubles(100, 0, 1.001)
                    .map(x -> (x >= 1) ? 1 : x)
                    .boxed()
                    .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            List<List<Double>> distanceMatrix = Distance.matrix(points, new Euclidean());

             System.out.println("Distance Matrix: " + distanceMatrix);
}
}







