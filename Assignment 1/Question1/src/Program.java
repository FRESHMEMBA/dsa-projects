import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Program {
    public static void main(String [] args) {
        new Program();
    }

    public Program() {
        int userInput;  //File name confirmation
        WRAVStack list = new WRAVStack();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================================================");
        System.out.println("Input files:");
        System.out.println("\t1. SubmissionInputs.txt");
        System.out.println("\t2. TestInputs.txt");
        System.out.println();

        do {
            System.out.print("Enter 1 to use 'SubmissionInputs.txt' | Enter 2 to use 'TestInputs.txt': ");
            userInput = Integer.parseInt(scanner.next());
        } while (userInput != 1 && userInput != 2);


        String inputFile = (userInput == 1) ? "SubmissionInputs.txt" : "TestInputs.txt";

        list = readFile(inputFile);
        reverseList(list);
        
        scanner.close();
        System.out.println("==============================================================================");
    }

    public void reverseList(WRAVStack stack) {
        String outputFile = "Outputs.txt";

        try (PrintWriter printWriter = new PrintWriter(new File(outputFile))) {
            while (!stack.isEmptyStack()) {
                printWriter.println(stack.pop());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nList reversed successfully. Open 'Outputs.txt' to view reversed list.\n");
    }

    private WRAVStack readFile(String filename) {
        WRAVStack newStack = new WRAVStack();

        try (Scanner scanner = new Scanner(new File(filename))) {
            String newStackItem;

            while (scanner.hasNext()) {
                newStackItem = scanner.nextLine();
                newStack.push(newStackItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newStack;
    }
}
