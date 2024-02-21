package lab2;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class MyMutation implements EvolutionaryOperator<double[][]> {

    public int nToChange;

    public MyMutation(int nToChange) {
        this.nToChange = nToChange;
    }
    public List<double[][]> apply(List<double[][]> population, Random random) {
        for (double[][] solution : population) {
            for (int i = 0; i < nToChange; i++) {
                int x1, y1, x2, y2;
                do {
                    x1 = random.nextInt(solution.length);
                    y1 = random.nextInt(solution.length);
                    x2 = random.nextInt(solution.length);
                    y2 = random.nextInt(solution.length);
                } while (solution[x1][y1] != 1 && solution[x2][y2] != 0);

                solution[x1][y1] = 0;
                solution[x2][y2] = 1;
            }
        }

        return population;
    }
}
