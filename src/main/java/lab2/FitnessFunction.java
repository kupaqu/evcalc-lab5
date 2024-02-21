package lab2;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FitnessFunction implements FitnessEvaluator<double[][]> {

    public FitnessFunction(int dimension) {
        this.dimension = dimension;
    }

    private int dimension;

    public double getFitness(double[][] solution, List<? extends double[][]> list) {
        return xcollisions(solution) + ycollisions(solution);
    }

    private double xcollisions(double[][] solution) {
        double sum = 0;
        for (int x=0; x<dimension; x++) {
            double ones = Arrays.stream(solution[x]).sum();
            sum += Math.abs(ones - 1);
        }
        return sum;
    }
    private double ycollisions(double[][] solution) {
        double sum = 0;
        for (int y=0; y<dimension; y++) {
            double ones = 0;
            for (int x = 0; x < dimension; x++) {
                if(solution[x][y] == 1) {
                    ones += 1;
                }
            }
            sum += Math.abs(ones - 1);;
        }
        return sum;
    }



    public boolean isNatural() {
        return false;
    }
}
