import csc1035.project3.StockCount;
import java.util.Scanner;
public class CommandLineInterface {
    public static CommandLineInterface interfaceObj = new CommandLineInterface();
    public void runCLI(){
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please select one of the following options:");
        System.out.println("Option 1: Retrieve count of available stock");
        System.out.println("Option 2: Customer Transaction");
        System.out.println("Option 3: Update Stock");
        System.out.println("Please input option 1, 2, or 3");
        String userChoice1 = scannerObj.nextLine();
        while (!((userChoice1.equals("1"))||  (userChoice1.equals("2"))  ||  (userChoice1.equals("3"))))   {
            System.out.println("Incorrect input: Please Input 1, 2 or 3");
            runCLI();
            break;
        }
        switch(userChoice1){
            case "1":
                System.out.println("Stock count:");
                StockCount.count();
                break;
            case "2":
                System.out.println("Customer Transaction:");
                break;
            case "3":
                System.out.println("Update stock:");
                break;
            }
        }




    public static void main(String[] args) {
    interfaceObj.runCLI();
    }
}
