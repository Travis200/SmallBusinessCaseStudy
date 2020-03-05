package csc1035.project3;

import java.util.*;

public class Receipt {


    public static void AsciiTable(List<String> itemNames, List<Integer> itemPrices){

        //Formatting ASCII table
        String leftAlignFormat="|  %-33s    %-4s |%n";
        String leftAlignFormatTotal="|                                       %-4s |%n";
        //This is how the start of every receipt will look like
        System.out.format("+--------------------------------------------+%n");
        System.out.format("|                 Cash Receipt               | %n");
        System.out.format("+--------------------------------------------+%n");
        System.out.format("|                                            | %n");
        System.out.format("|               Tricky Trinkets              | %n");
        System.out.format("|                                            | %n");
        System.out.format("+--------------------------------------------+%n");
        System.out.format("|                                            | %n");
        System.out.format("|  Item:                              Price: | %n");
        System.out.format("|                                            | %n");

        //Looping through the transaction to print the names and prices of all the items that the customer just bought.
        for(int i=0; i<itemNames.size(); i++){
            //Prints out the results found in the format of leftAlignFormat so the structure of the ASCII table remains.
            System.out.format(leftAlignFormat,itemNames.get(i),itemPrices.get(i));
        }

        System.out.format("+--------------------------------------------+%n");
        System.out.format("|  Total Cost:                               | %n");

        //Looping through the transaction to find the total cost and print it
        int total = 0;

        for (int i=0; i<itemPrices.size(); i++){
            total += itemPrices.get(i);
        }
        //After the total cost has been calculated, it prints it out onto the ASCII table in the format of
        //leftAlignFormatTotal to not break the structure of the table.
        System.out.format(leftAlignFormatTotal, total);
        System.out.format("+--------------------------------------------+%n");


        //Closing out the ASCII table at the bottom after everything else has been printed
        System.out.format("|                                            |%n");
        System.out.format("|                  Thank You                 |%n");
        System.out.format("+--------------------------------------------+%n");

        }
}