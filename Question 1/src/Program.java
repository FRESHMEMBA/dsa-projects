import wrav.datastructures.*;
import wrav.utilities.FileIO;

import java.util.Scanner;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        new Program();
    }

    public Program() {
        boolean printToConsole = true;
        String outputFile = "Outputs.txt";
        String inputFile = getInputFilename();
        ConvertToPostfix(inputFile, outputFile, printToConsole);
    }

    private void ConvertToPostfix(String intputFile, String outputFile, boolean printToConsole) {
        try (Scanner fileScanner = new Scanner(new File(intputFile))) {
            while (fileScanner.hasNextLine()) {
                WRAVStack theStack = new WRAVStack();
                WRAVQueue theQueue = new WRAVQueue();

                String[] lineTokens = fileScanner.nextLine().split(" ");    // "\\s+"
                
                for (String lineToken : lineTokens) {
                    if (isNumeric(lineToken)) {
                        theQueue.enqueue(lineToken);

                    } else {  //it is an operator
                        while (!theStack.isEmptyStack() && 
                                hasHigherPredence(theStack.peek(), lineToken)) {
                            // Pop and enqueue all operators with higher precedence
                            theQueue.enqueue(theStack.pop());
                        }

                        // Push the current operator
                        theStack.push(lineToken);
                    }
                }

                // Enqueue all the remaining operators from the stack
                while (!theStack.isEmptyStack()) {
                    theQueue.enqueue(theStack.pop());
                }

                if (printToConsole) System.out.println(theQueue);

                FileIO.writeToFile(theQueue, "Outputs.txt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * hasHigherPrecedence - checks if the first parameter (operator) has
     * higher precedence than the second parameter (operator).
     * @firstOperator: the first operator to be checked for higher precedence.
     * @secondOperator: the second operator to be checked for higher precedence.
     * @return true if the first operator has higher precedence than the second
     * operator; otherwise, return false.
     */
    private boolean hasHigherPredence(String firstOperator, String secondOperator) {
        return getPrecedence(firstOperator) > getPrecedence(secondOperator);
    }

    private int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-"))
            return 1;

        if (operator.equals("*") || operator.equals("/"))
            return 2;

        return 0;
    }

    private boolean isNumeric(String str) {
        if (str == null) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getInputFilename() {
        String userConfirmation;
        String submissionFile = "SubmissionInputs.txt";
        String testingFile = "TestInputs.txt";

        Scanner consoleScanner = new Scanner(System.in);

        do {
            System.out.print("Enter 'S' to submit | Enter 'T' to test: ");
            userConfirmation = consoleScanner.next();
        } while (!userConfirmation.equalsIgnoreCase("S") && !userConfirmation.equalsIgnoreCase("T"));

        consoleScanner.close();

        return (userConfirmation.equalsIgnoreCase("S")) ? submissionFile : testingFile;
    }
}
