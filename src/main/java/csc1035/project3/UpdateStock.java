package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Scanner;

public class UpdateStock {
//    This creates an object of the class.
    public static UpdateStock updateStockObj = new UpdateStock();
    /**
     * Runs command line style user interface where the user chooses how they would like to update the database.
     * The user has a choice to either overwrite an existing stock number from an item already in the database,
     * add a completely new item, or delete an item from the database.
     * Depending on which choice the user chooses the correlating method will be run.
     */
    public void runUpdateStockCLI() {
        boolean valid = true;
        while (valid) {
            System.out.println("Please select one of the following options:");
            System.out.println("Option 1: Overwrite existing stock number");
            System.out.println("Option 2: Add new entry");
            System.out.println("Option 3: Delete entry");
            System.out.println("Option 4: Exit");
            System.out.println("Please input option 1, 2, 3 or 4");
//            This creates a scanner object which is used for the user input.
            Scanner scannerObj = new Scanner(System.in);
            String userChoice = scannerObj.nextLine().trim();
//            This validates the user input is a valid option.
            if (!((userChoice.equals("1")) || (userChoice.equals("2")) || (userChoice.equals("3")) || (userChoice.equals("4")))) {
                System.out.println("Incorrect input: Please Input 1, 2, 3 or 4");
            } else {
                boolean runMethod = true;
//                Depending on the user choice each case will run a different method.
                switch (userChoice) {
                    case "1":
                        while (runMethod == true) {
                            Scanner scannerObj2 = new Scanner(System.in);
                            System.out.println("Overwrite existing stock number");
                            System.out.println("Please enter the the name of the product you would like to update: ");
                            String productName = scannerObj2.nextLine().trim();
                            while (checkItemExists(productName) == false) {
                                System.out.println("This product does not exist, please enter a valid product: ");
                                productName = scannerObj2.nextLine().trim();
                            }
                            System.out.println("Please enter the the number of the value you would like to overwrite it with: ");
                            int updateValue = scannerObj.nextInt();
                            while (updateValue < 0) {
                                System.out.println("Invalid input, please enter a stock number equal to or higher than 0: ");
                                updateValue = scannerObj.nextInt();
                            }
                            updateStockValue(productName, updateValue);
                            runMethod = runAgain();
                        }
                        break;
                    case "2":
                        while (runMethod == true) {
                            System.out.println("Add new entry");
                            createNewStockEntry();
                            runMethod = runAgain();
                        }
                        break;
                    case "3":
                        while (runMethod == true) {
                            System.out.println("Delete entry");
                            System.out.println("Please enter the item you would like to delete from the database: ");
                            String userItemToDel = scannerObj.nextLine().trim();
                            while (checkItemExists(userItemToDel) == false) {
                                System.out.println("This product does not exist, please enter a valid product: ");
                                userItemToDel = scannerObj.nextLine().trim();
                            }
                            deleteStockEntry(userItemToDel);
                            runMethod = runAgain();
                        }
                        break;
                    case "4":
                        valid = false;
                }
            }
        }
    }

    /**
     * Updates the stock value of an item in the database.
     * @param productName Name of the product which stock value will be changed
     * @param updateValue Value which will be used to overwrite the current stock value.
     */
    public void updateStockValue(String productName, int updateValue) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        Makes a select query.
        Query q = session.createQuery("select o from Stock o where stockName =:value");
//        Sets the "value" equal to the productName.
        q.setParameter("value", productName);
        session.getTransaction().commit();
        for (Object i : q.getResultList()) {
            Stock tmp = (Stock) i;
//            This updates the database with the new value
            tmp.setStock(updateValue);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tmp);
            session.getTransaction().commit();
            session.close();
        }
    }

    /**
     * Creates a item in the database based on what the user inputs.
     */
    public void createNewStockEntry() {
//        Will add entries to the database to the DB.
        Scanner scannerObj = new Scanner(System.in);
        Stock newStock = new Stock();
        System.out.println("Please enter the the name of the product: ");
        String userStockName = scannerObj.nextLine().toLowerCase().trim();
//            Validates that the item name does not already exist in the DB.
        while (checkItemExists(userStockName) == true) {
            System.out.println("This product already exists, please enter a new product: ");
            userStockName = scannerObj.nextLine().toLowerCase().trim();
        }
        System.out.println("Please enter the the category of the product: ");
        String userStockCategory = scannerObj.nextLine().toLowerCase().trim();
        System.out.println("Is the product perishable: ");
        System.out.println("Y = yes, N = no");
        String userStockPerishable = scannerObj.nextLine();
        boolean userStockPerishableBool = stringToBool(userStockPerishable);
        System.out.println("What is the cost of the product: ");
        double userStockCost = scannerObj.nextDouble();
//            Validates the user enters a valid cost.
        while (userStockCost < 0) {
            System.out.println("Invalid input, please enter a cost equal to or higher than 0: ");
            userStockCost = scannerObj.nextDouble();
        }
        System.out.println("What is the stock of the product: ");
        int userStockNum = scannerObj.nextInt();
//            Validates the user enters a valid stock number.
        while (userStockNum < 0) {
            System.out.println("Invalid input, please enter a stock number equal to or higher than 0: ");
            userStockNum = scannerObj.nextInt();
        }
        System.out.println("What is the sell price of the product: ");
        double userStockSellPrice = scannerObj.nextDouble();
//            Validates the user enters a valid sell price.
        while (userStockSellPrice < 0) {
            System.out.println("Invalid input, please enter a sell price equal to or higher than 0: ");
            userStockSellPrice = scannerObj.nextDouble();
        }
        boolean addToDatabaseBool = true;
//            Warns the user if the cost is higher than the sell price.
        if (userStockCost > userStockSellPrice) {
            System.out.println("The cost is higher than the sell price, are you sure you wish to add this item?");
            System.out.println("Y = add to the database anyway, N = dont add to the database");
            String addToDatabase = scannerObj.nextLine().toUpperCase().trim();
            addToDatabaseBool = stringToBool(addToDatabase);
        }
//            Adds the entry to the database.
        if (addToDatabaseBool == true) {
            newStock.setStockName(userStockName);
            newStock.setcategory(userStockCategory);
            newStock.setperishable(userStockPerishableBool);
            newStock.setCost(userStockCost);
            newStock.setStock(userStockNum);
            newStock.setSellPrice(userStockSellPrice);

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(newStock);
            session.getTransaction().commit();
            session.close();
        }
    }


    /**
     * Deletes an item from the database using the product name.
     * @param productName The name of the product that will be deleted.
     */
    public void deleteStockEntry(String productName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Finds the stockID associated with the item
        session.beginTransaction();
        Query q = session.createQuery("select o from Stock o where stockName =:value");
        q.setParameter("value", productName);
        session.getTransaction().commit();
        int stockIdToDel = 0;
        for (Object i : q.getResultList()) {
            Stock tmp = (Stock) i;
            stockIdToDel = tmp.getId();
        }
        session.close();
//        Uses stockID to delete the item from te DB.
        Stock stockToDel;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        stockToDel = session.get(Stock.class, stockIdToDel);
        session.delete(stockToDel);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Checks if an item already exists in the database.
     * @param itemToCheck The name of the item we are checking.
     * @return Boolean value (true = the item exists, false = the item does not exist).
     */
    public boolean checkItemExists(String itemToCheck) {
        boolean doesExist = true;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("select o from Stock o where stockName =:value");
            q.setParameter("value", itemToCheck);
            session.getTransaction().commit();
            if (q.getResultList().size() == 0) {
                doesExist = false;
        }
        return doesExist;
    }

    /**
     * This method converts the string Y or N to a boolean value.
     * @param stringToConvert The string to be converted.
     * @return If the string is "Y" the method returns true else if is "N" the false is returned.
     */
    public boolean stringToBool(String stringToConvert) {
        stringToConvert = stringToConvert.toUpperCase().trim();
        Scanner scannerObj = new Scanner(System.in);
        boolean convertedString = false;
        while(!((stringToConvert.equals("Y")) || (stringToConvert.equals("N")))) {
            System.out.println("Invalid input, please input Y or N: ");
            stringToConvert = scannerObj.nextLine().toUpperCase().trim();
        }
        if (stringToConvert.equals("Y")){
            convertedString = true;
        }
        return convertedString;
    }

    /**
     * Creates a user input where the user can choose whether to run a method again.
     * @return Boolean value (true = run again, false = do not run again).
     */
    public boolean runAgain() {
        System.out.println("Would you like to run this option again?");
        System.out.println("Y = yes, N = no");
        Scanner scannerObj = new Scanner(System.in);
        String runMethodAgain = scannerObj.nextLine();
        return stringToBool(runMethodAgain);
    }
}
