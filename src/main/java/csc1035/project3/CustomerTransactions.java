package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//import javax.management.Query;

public class CustomerTransactions {
    public static void main(String[] args) {transaction();}
    public static void transaction() {
        List<String> items = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        String item;
        List<String> itemNames = new ArrayList<>();
        List<Integer> itemPrices = new ArrayList<>();
        int itemcode = 3;
        do {
            System.out.println("What item would you like to add to the transaction or type false to end it");
            item = scanner.nextLine();
            items.add(item);

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

                    }
                }

            }catch(HibernateException e){}
            finally {
                session.close();}

        }
        //receipt(itemNames, itemPrices);
    }
}