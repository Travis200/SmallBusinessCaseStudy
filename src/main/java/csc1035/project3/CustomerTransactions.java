package csc1035.project3;
import java.util.*;
public class CustomerTransactions {
    public static void transaction(){
        Scanner scanner = new Scanner(System.in);
        String item;
        do{
            System.out.println("What item would you like to add to the transaction or type false to end it");
            item = scanner.nextLine();
            System.out.println("Added item to transaction");

        }
        while(! item.matches("false"));{

        }

    }
}
