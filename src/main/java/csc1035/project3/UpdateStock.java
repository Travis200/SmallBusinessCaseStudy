package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Scanner;

public class UpdateStock {
    public static UpdateStock updateStockObj = new UpdateStock();

    /**
     * Runs command line style user interface where the user chooses how they would like to update the database.
     * The user has a choice to either overwrite an existing stock number from an item already in the database,
     * add a completely new item, or delete an item from the database.
     * Depending on which choice the user chooses the correlating method will be run.
     */
    public void runUpdateStockCLI() {
        System.out.println("Please select one of the following options:");
        System.out.println("Option 1: Overwrite existing stock number");
        System.out.println("Option 2: Add new entry");
        System.out.println("Option 3: Delete entry");
        System.out.println("Option 4: Exit");
        boolean valid = true;
        while (valid) {
            System.out.println("Please input option 1, 2, 3 or 4");
            Scanner scannerObj = new Scanner(System.in);
            String userChoice = scannerObj.nextLine();
            if (!((userChoice.equals("1")) || (userChoice.equals("2")) || (userChoice.equals("3")) || (userChoice.equals("4")))) {
                System.out.println("Incorrect input: Please Input 1, 2, 3 or 4");
            } else {
                switch (userChoice) {
                    case "1":
                        System.out.println("Overwrite existing stock number");
                        System.out.println("Please enter the the name of the product you would like to update: ");
                        String productName = scannerObj.nextLine();
                        System.out.println("Please enter the the number of the value you would like to overwrite it with: ");
                        int updateValue = scannerObj.nextInt();
                        updateStockValue(productName, updateValue);
                        break;
                    case "2":
                        System.out.println("Add new entry");
                        createNewStockEntry();
                        break;
                    case "3":
                        System.out.println("Delete entry");
                        System.out.println("Please enter the item you would like to delete from the database: ");
                        String userItemToDel = scannerObj.nextLine();
                        deleteStockEntry(userItemToDel);
                        break;
                    case "4":
                        valid = false;
                        System.out.println("Bye");
                }
            }
        }
    }

    /**
     * This updates the stock value of an item in the database.
     * @param productName This is the name of the product which stock value will be changed
     * @param updateValue This is the value which will be used to overwrite the current stock value.
     */
    public void updateStockValue(String productName, int updateValue) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select o from Stock o where stockName =:value");
        q.setParameter("value", productName);
        session.getTransaction().commit();
        for (Object i : q.getResultList()) {
            Stock tmp = (Stock) i;
            System.out.println(tmp.getStock());
            tmp.setStock(updateValue);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tmp);
            session.getTransaction().commit();
            session.close();
        }
    }

    /**
     * This creates a item in the database based on what the user inputs.
     */
    public void createNewStockEntry() {
        Scanner scannerObj = new Scanner(System.in);
        Stock newStock = new Stock();
        System.out.println("Please enter the the name of the product: ");
        String userStockName = scannerObj.nextLine();
        System.out.println("Please enter the the category of the product: ");
        String userStockCategory = scannerObj.nextLine();
        System.out.println("Is the product perishable: ");
        boolean userStockPerishable = scannerObj.nextBoolean();
        System.out.println("What is the cost of the product: ");
        int userStockCost = scannerObj.nextInt();
        System.out.println("What is the stock of the product: ");
        int userStockNum = scannerObj.nextInt();
        System.out.println("What is the sell price of the product: ");
        int userStockSellPrice = scannerObj.nextInt();
        newStock.setStockName(userStockName);
        newStock.setcategory(userStockCategory);
        newStock.setperishable(userStockPerishable);
        newStock.setCost(userStockCost);
        newStock.setStock(userStockNum);
        newStock.setSellPrice(userStockSellPrice);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newStock);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * This deletes an item from the database using the product name.
     * @param productName The name of the product that will be deleted.
     */
    public void deleteStockEntry(String productName) {
        Scanner scannerObj = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select o from Stock o where stockName =:value");
        q.setParameter("value", productName);
        session.getTransaction().commit();
        int stockIdToDel = 0;
        for (Object i : q.getResultList()) {
            Stock tmp = (Stock) i;
            stockIdToDel = tmp.getId();
            System.out.println(stockIdToDel);
        }

        session.close();

        Stock stockToDel;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        stockToDel = session.get(Stock.class, stockIdToDel);
        session.delete(stockToDel);
        session.getTransaction().commit();
        session.close();
    }

}
