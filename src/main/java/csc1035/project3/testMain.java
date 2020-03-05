package csc1035.project3;
import org.hibernate.Session;
public class testMain {
    /** This allows you to add items to the database with its attributes.*/
    public static void main(String[] args) {

        Stock s1 = new Stock(01,"car","toys",true,10,0, 15);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(s1);
        session.getTransaction().commit();
        session.close();
    }
}
