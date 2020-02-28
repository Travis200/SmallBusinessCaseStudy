package csc1035.project3;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;
public class StockCount {
    public static void count() {
       System.out.println("The stock count is shown below:");
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<Stock> stocks = session.createQuery("FROM Stock").list();
            for (Iterator<Stock> iterator = stocks.iterator(); iterator.hasNext();){
                Stock stock = iterator.next();
                System.out.print("Name: " + stock.getStockName());
                System.out.print("Quantity: " + stock.getStock());

            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
