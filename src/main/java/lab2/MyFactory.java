package lab2;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

public class MyFactory extends AbstractCandidateFactory<double[][]> {

    private int dimension;

    public MyFactory(int dimension) {
        this.dimension = dimension;
    }

    public double[][] generateRandomCandidate(Random random) {
        double[][] solution = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            int x = random.nextInt(dimension);
            int y = random.nextInt(dimension);
            solution[x][y] = 1;
        }

        return solution;
    }
}

