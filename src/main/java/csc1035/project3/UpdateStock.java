package csc1035.project3;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.query.Query;

public class UpdateStock {
    public static UpdateStock obj = new UpdateStock();
    public void updateStock() {
        //try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select o from Stock o where stockName =:value");
        q.setParameter("value", "candy");
        session.getTransaction().commit();

        for (Object i: q.getResultList()) {
            Stock tmp = (Stock) i;
            System.out.println(tmp.getStock());
            tmp.setStock(5);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tmp);
            session.getTransaction().commit();
            session.close();
        }



//        Stock  = (session.get(Stcck.class, 25));
//        car.setYear(2017);
//        session.update(car);
        //}
//        catch (HibernateException e) {
//            if (session!=null) session.getTransaction().rollback();
//            e.printStackTrace();
//        }
        //finally {
        session.close();
        //}
    }










    public static void main(String[] args) {
        obj.updateStock();
//        Stock s1 = new Stock(01,"horse","toys",true,10,100, 15);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(s1);
//        session.getTransaction().commit();
//        session.close();

    }
}
