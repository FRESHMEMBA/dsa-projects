import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        new Program();
    }

    public Program() {
        int userInput;  //File name confirmation
        String outputFile = "Outputs.txt";
        WRAVStack stack = new WRAVStack();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==============================================================================");
        System.out.println("Input files:");
        System.out.println("\t1. SubmissionInputs.txt");
        System.out.println("\t2. TestInputs.txt");
        System.out.println();

        do {
            System.out.print("Enter 1 to use 'SubmissionInputs.txt' | Enter 2 to use 'TestInputs.txt': ");
            userInput = Integer.parseInt(scanner.next());
        } while (userInput != 1 && userInput != 2);


        String inputFile = (userInput == 1) ? "SubmissionInputs.txt" : "TestInputs.txt";

        stack = readFile(inputFile);
        writeFile(stack, outputFile);
        
        scanner.close();

        System.out.println("\nFile processed successfully. Open 'Outputs.txt' to view the output results.");
        System.out.println("==============================================================================\n");
    }

    public WRAVStack readFile(String filename) {
        WRAVStack newStack = new WRAVStack();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                String lineString = scanner.nextLine();

                if (lineString.startsWith("+"))
                    newStack.push(lineString.replace("+", ""));
                else
                    newStack.pop();
                // if (lineString.equals("-") && !newStack.isEmptyStack())
                //     newStack.pop();
                // else
                //     newStack.push(lineString.replace("+", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newStack;
    }

    public void writeFile(WRAVStack stack, String filename) {
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            while (!stack.isEmptyStack()) {
                pw.println(stack.pop());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
