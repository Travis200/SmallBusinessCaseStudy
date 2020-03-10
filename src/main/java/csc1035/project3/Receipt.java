package csc1035.project3;

import java.util.*;

public class Receipt {


    /**
     * This method generates a receipt for the costumer after a purchase has happened.
     * First, the method asks how much the costumer paid, if it's less than the total it asks the costumer to pay enough
     * If it is enough, then an ASCII table is made in order to structure the receipt.
     * It then prints the items that are part of the transaction, as well as their price.
     * Following this, it prints the total cost, how much the costumer paid, and how much change is owed.
     *
     * @param itemNames
     * @param itemPrices
     */
    public static void AsciiTable(List<String> itemNames, List<Double> itemPrices) {

        //Looping through the transaction to find the total cost of the purchase.
        double total = 0.0;

        for (int i = 0; i < itemPrices.size(); i++) {
            total += itemPrices.get(i);
        }

        //Printing total cost so the customer knows how much to pay.
        System.out.println("the total cost is " + total);
        System.out.println();

        //Asking how much the costumer paid and making sure that it isn't less than what is owed
        Double amountPaid = 0.0;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter how much the costumer paid ");
            System.out.println("(Please ensure its more than or equal to " + total + "): ");
            if (input.hasNextDouble()){
                amountPaid = input.nextDouble();}
        }while (amountPaid < total);

        //Formatting ASCII table so the addition of variables doesn't hinder the format
            String leftAlignFormat = "|  %-33s    %-4s |%n";
            String leftAlignFormatTotal = "|  Total Cost:                          %-4s |%n";
            String leftAlignFormatAmountPaid = "|  Amount Paid:                         %-4s|%n";
            String leftAlignFormatChangeOwed = "|  Change Owed:                         %-4s |%n";

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

            //Looping through the transaction to print the names and prices of all the items that the customer just
            //bought.
            for (int i = 0; i < itemNames.size(); i++) {
                //Prints out the results found in the format of leftAlignFormat so the structure of the ASCII table
                //remains.
                System.out.format(leftAlignFormat, itemNames.get(i), itemPrices.get(i));
            }

            System.out.format("+--------------------------------------------+%n");
            System.out.format("|                                            |%n");
            ;


            //After the total cost has been calculated, it prints it out onto the ASCII table in the format of
            //leftAlignFormatTotal to not break the structure of the table.
            System.out.format(leftAlignFormatTotal, total);
            System.out.format("|                                            |%n");
            System.out.format("+--------------------------------------------+%n");

            //Then, it will show how much the costumer paid, subtract the total, in order to calculate the change.
            Double change = amountPaid - total;
            System.out.format("|                                            |%n");
            System.out.format(leftAlignFormatAmountPaid, amountPaid);
            System.out.format("|                                            |%n");
            System.out.format(leftAlignFormatChangeOwed, String.format("%.2f", change));
            System.out.format("|                                            |%n");
            System.out.format("+--------------------------------------------+%n");


            //Closing out the ASCII table at the bottom after everything else has been printed
            System.out.format("|                                            |%n");
            System.out.format("|                  Thank You                 |%n");
            System.out.format("+--------------------------------------------+%n");
            System.out.println();

        }
    }

