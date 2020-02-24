import java.util.*;

/**
 * Write a description of class Brain here.
 *
 * @author Aaron Fink
 * @version October 31, 2019
 */
public class Brain {
    double[][][] weights;
    double[][] biases;

    public Brain(double[][][] w, double[][] b) {
        // Set the weights
        weights = new double[w.length][][];
        for(int layer = 0; layer < w.length; layer++) {
            weights[layer] = new double[w[layer].length][];
            for(int firstNeuron = 0; firstNeuron < w[layer].length; firstNeuron++) {
                weights[layer][firstNeuron] = new double[w[layer][firstNeuron].length];
                for(int secondNeuron = 0; secondNeuron < w[layer][firstNeuron].length; secondNeuron++) {
                    weights[layer][firstNeuron][secondNeuron] = w[layer][firstNeuron][secondNeuron];
                }
            }
        }
        
        // Set the biases
        biases = new double[b.length][];
        for(int layer = 0; layer < b.length; layer++) {
            biases[layer] = new double[b[layer].length];
            for(int neuron = 0; neuron < b[layer].length; neuron++) {
                biases[layer][neuron] = b[layer][neuron];
            }
        }
    }
    
    // Propogate inputs forward through the neural network
    public double[] processInputs(double[] inputs) {
        double[][] values = new double[biases.length + 1][];
        values[0] = inputs;
        for(int layer = 1; layer < values.length; layer++) {
            values[layer] = new double[biases[layer - 1].length];
            for(int neuron = 0; neuron < values[layer].length; neuron++) {
                values[layer][neuron] = biases[layer - 1][neuron];
                for(int previousNeuron = 0; previousNeuron < values[layer - 1].length; previousNeuron++) {
                    values[layer][neuron] += values[layer - 1][previousNeuron] * weights[layer - 1][previousNeuron][neuron];
                }
                values[layer][neuron] = activate(values[layer][neuron]);
            }
        }
        return values[values.length - 1];
    }
    
    // currently uses the logistic function
    public double activate(double value) {
        return 1.0 / (1.0 + Math.exp(-value));
    }
    
    public static Brain initializeRandom(double[][][] weightsDeviation, double[][] biasesDeviation) {
        Random randomizer = Manager.randomizer;
        
        double[][][] weights = new double[weightsDeviation.length][][];
        for(int layer = 0; layer < weights.length; layer++) {
            weights[layer] = new double[weightsDeviation[layer].length][];
            for(int firstNeuron = 0; firstNeuron < weights[layer].length; firstNeuron++) {
                weights[layer][firstNeuron] = new double[weightsDeviation[layer][firstNeuron].length];
                for(int secondNeuron = 0; secondNeuron < weights[layer][firstNeuron].length; secondNeuron++) {
                    double weight = randomizer.nextGaussian() * weightsDeviation[layer][firstNeuron][secondNeuron];
                    weights[layer][firstNeuron][secondNeuron] = weight;
                }
            }
        }
        
        double[][] biases = new double[biasesDeviation.length][];
        for(int layer = 0; layer < biases.length; layer++) {
            biases[layer] = new double[biasesDeviation[layer].length];
            for(int neuron = 0; neuron < biases[layer].length; neuron++) {
                double bias = randomizer.nextGaussian() * biasesDeviation[layer][neuron];
                biases[layer][neuron] = bias;
            }
        }
        
        return new Brain(weights, biases);
    }
    
    public static Brain simulateReproduction(Brain firstBrain, Brain secondBrain) throws BrainMismatchException {
        Random randomizer = Manager.randomizer;
        
        double[][][] weights = new double[firstBrain.weights.length][][];
        for(int layer = 0; layer < weights.length; layer++) {
            weights[layer] = new double[firstBrain.weights[layer].length][];
            for(int firstNeuron = 0; firstNeuron < weights[layer].length; firstNeuron++) {
                weights[layer][firstNeuron] = new double[firstBrain.weights[layer][firstNeuron].length];
                for(int secondNeuron = 0; secondNeuron < weights[layer][firstNeuron].length; secondNeuron++) {
                    double average;
                    try {
                        average = (firstBrain.weights[layer][firstNeuron][secondNeuron] + secondBrain.weights[layer][firstNeuron][secondNeuron]) / 2;
                    } catch(ArrayIndexOutOfBoundsException e) {
                        throw new BrainMismatchException("Two brains of different sizes tried to reproduce.");
                    }
                    double weight = randomizer.nextGaussian() * average / 10 + average;
                    weights[layer][firstNeuron][secondNeuron] = weight;
                }
            }
        }
        
        double[][] biases = new double[firstBrain.biases.length][];
        for(int layer = 0; layer < biases.length; layer++) {
            biases[layer] = new double[firstBrain.biases[layer].length];
            for(int neuron = 0; neuron < biases[layer].length; neuron++) {
                double average;
                try {
                    average = (firstBrain.biases[layer][neuron] + secondBrain.biases[layer][neuron]) / 2;
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new BrainMismatchException("Two brains of different sizes tried to reproduce.");
                }
                double bias = randomizer.nextGaussian() * average / 10 + average;
                biases[layer][neuron] = bias;
            }
        }
        
        return new Brain(weights, biases);
    }
}
