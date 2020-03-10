package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.management.Query;

public class CustomerTransactions {
/**
*Takes a user input of a string and an int with a space in between for example
 * ring 3
 * it will add 3 rings to the transaction
 * or you can type false to stop adding to the transaction
 * */
public static void transaction() {
    List<String> items = new ArrayList<>();
    Session session = HibernateUtil.getSessionFactory().openSession();
    Scanner scanner = new Scanner(System.in);
    String item = "";
    int quantity = 1;
    List<String> itemNames = new ArrayList<>();//A list of items to pass to the receipt
    List<Double> itemPrices = new ArrayList<>();//A list of prices to pass to the receipt
    do {
        System.out.println("What item would you like to add to the transaction followed by the quantity with a space in between");
        System.out.println("or type false to end it");
        String input1 = scanner.nextLine();
        Scanner scanner2 = new Scanner(input1).useDelimiter("\\s");//splits the scanner item by the space between them
        if (scanner2.hasNext()){//checks if scanner has a next item to use
            item = (scanner2.next());
            if (item.matches("false")){
                break;
            }
            if (scanner2.hasNextInt()){//checks if scanner has a next integer to use
                quantity = (scanner2.nextInt());}
            scanner2.close();}


        try {
            for (int i = 0; i < quantity; i++) {//loops to add an item to the transaction based on the quantity
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query q = session.createQuery("select o from Stock o where stockName =:value");
                q.setParameter("value", item);//sets the query to select the item to be added to the transaction
                session.getTransaction().commit();
                if (q.getResultList().size()==0){//If no values returned the item is not in the database
                    System.out.println("Item does not exist");
                    break;
                }
                for (Object j : q.getResultList()) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Stock tmp = (Stock) j;

                    if (tmp.getStock() <= 0) {//if the stock is 0 or less its out of stock and cannot be bought
                        System.out.println();
                        System.out.println("Out of Stock : " + tmp.getStockName());
                        break;
                    } else if(tmp.getStock()>0) {//if the stock is above 0 its added to the transaction
                        tmp.setStock(tmp.getStock() - 1);
                        session.update(tmp);
                        session.getTransaction().commit();
                        itemNames.add(tmp.getStockName());
                        itemPrices.add(tmp.getSellPrice());
                        System.out.println("Item added to transaction");
                        break;
                    }

                }
            }

        } catch (HibernateException e) {
        } finally {
            session.close();
        }

    }
    while (!item.matches("false"));
    {
        if(itemNames.size()>0) {
            Receipt.AsciiTable(itemNames, itemPrices);
        }
    }
}
}
