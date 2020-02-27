package csc1035.project3;
import org.hibernate.Session;
public class testMain {
    public static void main(String[] args) {

        Stock s1 = new Stock(01,"ring","jewels",false,100,10, 150);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);
        session.getTransaction().commit();
        session.close();
    }
}
