package csc1035.project3;
import org.hibernate.*;
import java.util.*;
public class CRUD {
    /** This allows you to add items to the database with its attributes.*/
    public static void create(int stock, String category, int cost, int sellprice, boolean perishable, String name){

        Stock s1 = new Stock();
        s1.setStock(stock);
        s1.setcategory(category);
        s1.setCost(cost);
        s1.setSellPrice(sellprice);
        s1.setperishable(perishable);
        s1.setStockName(name);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);
        session.getTransaction().commit();
        session.close();
    }
    public static void read(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List stock = session.createQuery("FROM Stock").list();
            for (Iterator<Stock> iterator = stock.iterator(); iterator.hasNext();){
                Stock stocks = iterator.next();
                System.out.print("Make: " + stocks.getStockName());
                System.out.print("Make: " + stocks.getCost());
                System.out.print("Make: " + stocks.category());
                System.out.print("Make: " + stocks.getSellPrice());
                System.out.print("Make: " + stocks.perishable());
                System.out.print("Make: " + stocks.getStock());
                System.out.print("Make: " + stocks.getId());

            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void update(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Stock stock = (session.get(Stock.class, id));
            stock.setSellPrice( 20 );
            session.update(stock);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void delete(int id){
    Session session = HibernateUtil.getSessionFactory().openSession();
     try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Stock stock = session.get(Stock.class, id);
        session.delete(stock);
        session.getTransaction().commit();
    } catch (HibernateException e) {
        if (session!=null) session.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}
}
