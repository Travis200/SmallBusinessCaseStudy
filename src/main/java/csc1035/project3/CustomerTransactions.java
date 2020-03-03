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
        int itemcode = 3;
        do {
            System.out.println("What item would you like to add to the transaction or type false to end it");
            item = scanner.nextLine();
            items.add(item);

        }
        while (!item.matches("false"));
        {
            try{
                for(int i=0;i<items.size();i++)
                session = HibernateUtil.getSessionFactory().openSession();
                System.out.println("Added item to transaction");
                session.beginTransaction();
                Stock stock = (session.get(Stock.class, itemcode));
                Query q = session.createQuery("select o from Stock o where stockName =:value");
                q.setParameter("value", "ring");
                session.getTransaction().commit();
                System.out.println(q);
                for (Object i : q.getResultList()) {
                    Stock tmp = (Stock) i;
                    System.out.println(tmp.getStockName());
                    
                }
                //System.out.println(stock.getSellPrice());
                //stock.setStock(stock.getStock()-1);
                //System.out.println(stock.getStock());
                //session.update(stock);
                //session.save(stock);
                //session.getTransaction().commit();
            }catch(HibernateException e){}
            finally {
                session.close();}

        }
    }
}