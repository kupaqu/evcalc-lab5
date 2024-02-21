package lab2;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RankSelection;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MyAlg {

    public static void main(String[] args) {
        int dimension = 32; // dimension of problem
        int populationSize = 100; // size of population
        int generations = 1000; // number of generations
        int nToChange = 5;

        Random random = new Random(); // random

        CandidateFactory<double[][]> factory = new MyFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<double[][]>> operators = new ArrayList<EvolutionaryOperator<double[][]>>();
        operators.add(new MyCrossover()); // Crossover
        operators.add(new MyMutation(nToChange)); // Mutation
        EvolutionPipeline<double[][]> pipeline = new EvolutionPipeline<double[][]>(operators);

//        SelectionStrategy<Object> selection = new TournamentSelection(new Probability(0.95)); // Selection operator
//        SelectionStrategy<Object> selection = new RankSelection();
        SelectionStrategy<Object> selection = new RouletteWheelSelection();
        FitnessEvaluator<double[][]> evaluator = new FitnessFunction(dimension); // Fitness function

        EvolutionEngine<double[][]> algorithm = new SteadyStateEvolutionEngine<double[][]>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                System.out.println("\tBest solution = " + Arrays.deepToString((double[][])populationData.getBestCandidate()));
                System.out.println("\tPop size = " + populationData.getPopulationSize());
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 1, terminate);
    }
}
