package csc1035.project3;
import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.management.Query;

public class CustomerTransactions {
    public static void main(String[] args) {transaction();}
    public static void transaction() {
        List<String> items = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        String item;
        do {
            System.out.println("What item would you like to add to the transaction or type false to end it");
            item = scanner.nextLine();
            System.out.println("Added item to transaction");
            items.add(item);

        }
        while (!item.matches("false"));
        {
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Stock stock = (session.get(Stock.class, items.get(0)));
                System.out.println(stock.getSellPrice());
            }catch(HibernateException e){}
            finally {
                session.close();}

        }
    }
}