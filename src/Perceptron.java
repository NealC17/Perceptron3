public class Perceptron {
    private int numInputs;
    private float[] weights;
    private float THRESHOLD = 0;

    private String classifyForLabel; // this is a classifier for eg "virginica"
    private float learningRate = 0.05f;

    public Perceptron(int numInputs, String whatToClassify) {
        this.classifyForLabel = whatToClassify;
        this.numInputs = numInputs;
        weights = initWeights(numInputs);
    }

    private float[] initWeights(int numInputs) {
        // TODO:  initialize the weights
        float[] out = new float[numInputs];

        for (int i = 0; i < numInputs; i++) {
            out[i]=(float)(2*Math.random()-1);
        }

        return out;
    }

    /***
     * Train the perceptron using the input feature vector and its correct label.
     * Return true if there was a non-zero error and training occured (weights got adjusted)
     *
     * @param input
     * @param correctLabel
     * @return
     */
    public boolean train(float[] input, String correctLabel) {
        // TODO:  Implement this.


        // run the perceptron on the input
        int guess = guess(input);

        // compare the guess with the correct label (can use already-made helper method for this).

        boolean isCorrect = isGuessCorrect(guess, correctLabel);

        // If guess was incorrect
        if(!isCorrect){
            //    update weights and THRESHOLD using learning rule
            float error;
            for(int i = 0; i < weights.length;i++){
                error = guess - getCorrectGuess(correctLabel);
                weights[i] -=input[i] * error * learningRate;
            }
            error = THRESHOLD-guess;
            THRESHOLD -= error* learningRate;

            return true;
        }

        return false;
    }

    public int guess(float[] input) {
        // TODO:  Implement this.
        // Do a linear combination of the inputs multiplied by the weights.
        float sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i]*weights[i];
        }
        // Run the sum through the activiationFunction and return the result
        return activationFunction(sum);
    }

    private int activationFunction(float sum) {
        if (sum > THRESHOLD) {
            return 1;
        } else {
            return 0;
        }
    }

    public float[] getWeights() {
        return weights;
    }

    public String getTargetLabel() {
        return this.classifyForLabel;
    }

    public boolean isGuessCorrect(int guess, String correctLabel) {
        return guess == getCorrectGuess(correctLabel);
    }

    /***
     * Return the correct output for a given class label.  ie returns 1 if input label matches
     * what this perceptron is trying to classify.
     * @param label
     * @return
     */
    public int getCorrectGuess(String label) {
        if (label.equals(this.classifyForLabel))
            return 1;
        else
            return 0;
    }

    public float getThreshold() {
        return THRESHOLD;
    }
}