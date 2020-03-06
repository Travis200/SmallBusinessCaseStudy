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
        List<String> itemNames = new ArrayList<>();
        List<Integer> itemPrices = new ArrayList<>();
        do {
            System.out.println("What item would you like to add to the transaction followed by the quantity with a space in between");
            System.out.println("or type false to end it");
            String input1 = scanner.nextLine();
            Scanner scanner2 = new Scanner(input1).useDelimiter("\\s");
            if (scanner2.hasNext()){
            item = (scanner2.next());
            if (item.matches("false")){
                break;
            }
            if (scanner2.hasNext()){
            quantity = (scanner2.nextInt());}
            scanner2.close();}


        }
        while (!item.matches("false"));
        {
            try{
                for(int i=0;i<items.size()-1;i++) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    System.out.println("Added item to transaction");
                    session.beginTransaction();
                    Query q = session.createQuery("select o from Stock o where stockName =:value");
                    q.setParameter("value", items.get(i));
                    session.getTransaction().commit();
                    for (Object j : q.getResultList()) {
                        session = HibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();
                        Stock tmp = (Stock) j;
                        System.out.println(tmp.getStockName());
                        tmp.setStock(tmp.getStock()-1);
                        System.out.println(tmp.getStock());
                        session.update(tmp);
                        session.getTransaction().commit();
                        System.out.println(tmp.getCost());
                        itemNames.add(tmp.getStockName());
                        itemPrices.add(tmp.getSellPrice());
                        break;

                    }
                }

            }catch(HibernateException e){}
            finally {
                session.close();}

        }
        Receipt.AsciiTable(itemNames, itemPrices);
    }
}