package csc1035.project3;

import java.util.*;

public class Receipt {


    public static void AsciiTable(List<String> itemNames, List<Integer> itemPrices){

        //Formatting ASCII table
        String leftAlignFormat="|  %-33s    %-4s |%n";
        String leftAlignFormatTotal="|                                       %-4s |%n";
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

        //Looping through the transaction to print all entries found
        for(int i=0; i<itemNames.size(); i++){
            System.out.format(leftAlignFormat,itemNames.get(i),itemPrices.get(i));
        }

        System.out.format("+--------------------------------------------+%n");
        System.out.format("|  Total Cost:                               | %n");

        //Looping through the transaction to find the total cost and print it
        int total = 0;

        for (int i=0; i<itemPrices.size(); i++){

            total += itemPrices.get(i);
        }
        System.out.format(leftAlignFormatTotal, total);
        System.out.format("+--------------------------------------------+%n");



        /*//Looping through the directory to print all entries found
                for (Entry entry : directory.toArrayList()) {
                    //With the alignment specified in String leftAlignFormat, it prints the entries in order of surname,
                    //initials, and extension.
                    System.out.format(leftAlignFormat, entry.getSurname(), entry.getInitials(), entry.getTelephoneExtension());
                }
                //Closing out the ASCII table at the bottom after all the entries have been printed*/
        System.out.format("|                                           |%n");
        System.out.format("|                  Thank You                 |%n");
        System.out.format("+--------------------------------------------+%n");

        }
}