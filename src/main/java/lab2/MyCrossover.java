package lab2;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCrossover extends AbstractCrossover<double[][]> {
    protected MyCrossover() {
        super(1);
    }

    protected List<double[][]> mate(double[][] p1, double[][] p2, int i, Random random) {
        ArrayList children = new ArrayList();

        int thresh = random.nextInt(p1.length);

        children.add(xcross(p1, p2, thresh));
        children.add(xcross(p2, p1, thresh));
        children.add(ycross(p2, p1, thresh));
        children.add(ycross(p2, p1, thresh));
        children.add(diagcross1(p1, p2, thresh));
        children.add(diagcross1(p2, p1, thresh));
        children.add(diagcross2(p1, p2, thresh));
        children.add(diagcross2(p2, p1, thresh));

        return children;
    }

    private double[][] xcross(double[][] p1, double[][] p2, int thresh) {
        double [][] child = new double[p1.length][p1.length];
        for (int x=0; x<p1.length; x++) {
            for (int y=0; y<p1.length; y++) {
                if (x < thresh) {
                    child[x][y] = p1[x][y];
                } else {
                    child[x][y] = p2[x][y];
                }
            }
        }
        return child;
    }

    private double[][] ycross(double[][] p1, double[][] p2, int thresh) {
        double [][] child = new double[p1.length][p1.length];
        for (int x=0; x<p1.length; x++) {
            for (int y=0; y<p1.length; y++) {
                if (y < thresh) {
                    child[x][y] = p1[x][y];
                } else {
                    child[x][y] = p2[x][y];
                }
            }
        }
        return child;
    }

    private double[][] diagcross1(double[][] p1, double[][] p2, int thresh) {
        double [][] child = new double[p1.length][p1.length];
        for (int x=0; x<p1.length; x++) {
            for (int y=0; y<p1.length; y++) {
                if (x < thresh && y < thresh) {
                    child[x][y] = p1[x][y];
                } else {
                    child[x][y] = p2[x][y];
                }
            }
        }
        return child;
    }

    private double[][] diagcross2(double[][] p1, double[][] p2, int thresh) {
        double [][] child = new double[p1.length][p1.length];
        for (int x=0; x<p1.length; x++) {
            for (int y=0; y<p1.length; y++) {
                if (x > thresh && y < thresh) {
                    child[x][y] = p1[x][y];
                } else {
                    child[x][y] = p2[x][y];
                }
            }
        }
        return child;
    }
}
