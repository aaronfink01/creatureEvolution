
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
    
    public double[] processInputs(double[] inputs) {
        return new double[0];
    }
    
    // currently uses the logistic function
    public double activate(double value) {
        return 1.0 / (1.0 + Math.exp(-value));
    }
}
