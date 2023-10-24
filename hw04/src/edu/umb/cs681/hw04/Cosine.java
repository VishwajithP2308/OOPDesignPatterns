package edu.umb.cs681.hw04;

import java.util.List;

public class Cosine implements DistanceMetric{
    public double distance(List<Double> p1, List<Double> p2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for(int i=0; i < p1.size(); i++) {
            dotProduct += p1.get(i)*p2.get(i);
            magnitude1 += Math.pow(p1.get(i), 2);
            magnitude2 += Math.pow(p2.get(i), 2);
        }

        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);

        if (p1.equals(p2)) {
            return 0.0;
        }

        if(magnitude1 != 0.0 && magnitude2 != 0.0) {

            return 1.0 - (dotProduct / (magnitude1 * magnitude2));
        }else {

            return 0.0;
        }
    }
}
