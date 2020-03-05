import csc1035.project3.StockCount;
import csc1035.project3.CustomerTransactions;
import java.util.Scanner;
public class CommandLineInterface {
    /** This is the command line interface shown to the user.*/
    public static CommandLineInterface interfaceObj = new CommandLineInterface();
    public void runCLI() {
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please select one of the following options:");
        System.out.println("Option 1: Retrieve count of available stock");
        System.out.println("Option 2: Customer Transaction");
        System.out.println("Option 3: Update Stock");
        System.out.println("Option 4: Exit");
        boolean valid = true;
        while (valid) {
            System.out.println("Please input option 1, 2, 3 or 4");
            String userChoice1 = scannerObj.nextLine();
            if (!((userChoice1.equals("1")) || (userChoice1.equals("2")) || (userChoice1.equals("3")) || (userChoice1.equals("4")))) {
                System.out.println("Incorrect input: Please Input 1, 2 or 3");
            }
            else { switch (userChoice1) {
                    case "1":
                        System.out.println("Stock count:");
                        StockCount.count();
                        break;
                    case "2":
                        System.out.println("Customer Transaction:");
                        CustomerTransactions.transaction();
                        break;
                    case "3":
                        System.out.println("Update stock:");
                        break;
                    case "4":
                        valid = false;
                        System.out.println("Bye");
                }
            }
        }
    }




    public static void main(String[] args) {
    interfaceObj.runCLI();
    }
}
