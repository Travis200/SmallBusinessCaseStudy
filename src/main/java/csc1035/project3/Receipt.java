package csc1035.project3;

public class Receipt (itemNames, itemPrices){


    public static void AsciiTable(Directory directory) {

        //Formatting ASCII table
        String leftAlignFormat = "|  %-33s    %-6s |%n";
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
        for (Entry entry : directory.toArrayList()) {
        //With the alignment specified in String leftAlignFormat, it prints the entries in order of Item, and Price
        System.out.format(leftAlignFormat, entry.getSurname(), entry.getInitials(), entry.getTelephoneExtension());
        }

        System.out.format("+--------------------------------------------+%n");
        System.out.format("|  Total Cost                                | %n");
        System.out.format("|                                            | %n");
        System.out.format("+--------------------------------------------+%n");


        //Looping through the directory to print all entries found
        for (Entry entry : directory.toArrayList()) {
            //With the alignment specified in String leftAlignFormat, it prints the entries in order of surname,
            //initials, and extension.
            System.out.format(leftAlignFormat, entry.getSurname(), entry.getInitials(), entry.getTelephoneExtension());
        }
        //Closing out the ASCII table at the bottom after all the entries have been printed
        System.out.format("                                             %n");
        System.out.format("                   Thank You                  %n");

    }
}