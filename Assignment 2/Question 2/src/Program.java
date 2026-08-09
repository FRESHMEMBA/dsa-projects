import java.util.Scanner;
import java.io.File;

import wrav.datastructures.*;
import wrav.utilities.*;

public class Program {
    public static void main(String[] args) {
        new Program();
    }

    public Program() {
        String inputFile = "TestInputs.txt";
        String outputFile = "Outputs.txt";
        boolean printToConsole = true;

        evaluatePostfix(inputFile, outputFile, printToConsole);
    }

    private void evaluatePostfix(String inputFile, String outputFile, boolean printToConsole) {
        try (Scanner fileScanner = new Scanner(new File(inputFile))) {
            while (fileScanner.hasNextLine()) {
                String[] tokens = fileScanner.nextLine().split(" ");
                WRAVQueue theQueue = createQueue(tokens);
                WRAVStack theStack = new WRAVStack();

                while (!theQueue.isEmptyQueue()) {
                    String current = theQueue.dequeue();

                    if (StringOps.isNumeric(current)) {
                        theStack.push(current);
                    } else {
                        double result;
                        double firstNum = Double.parseDouble(theStack.pop());
                        double secondNum = Double.parseDouble(theStack.pop());

                        switch (current) {
                            case "+":
                                result = firstNum + secondNum;
                                break;
                            case "-":
                                result = firstNum - secondNum;
                                break;
                            case "*":
                                result = firstNum * secondNum;
                                break;
                            default:
                                result = firstNum / secondNum;
                                break;
                        }

                        // theStack.push(String.valueOf(result));
                        theStack.push(String.format("%.2f", result));
                    }
                }

                // FileIO.writeToFile(theQueue, outputFile);
                System.out.println(theStack.peek());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WRAVQueue createQueue(String[] elements) {
        WRAVQueue queue = new WRAVQueue();

        for (String element : elements) {
            queue.enqueue(element);
        }

        return queue;
    }
}
