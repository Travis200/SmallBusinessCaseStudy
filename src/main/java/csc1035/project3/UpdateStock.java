package csc1035.project3;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Scanner;

public class UpdateStock {
    public static UpdateStock updateStockObj = new UpdateStock();
//    public static String productName;
//    public static int updateValue;

    public void runUpdateStockCLI() {
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please select one of the following options:");
        System.out.println("Option 1: Overwrite existing stock number");
        System.out.println("Option 2: Add/subtract from existing stock");
        System.out.println("Option 3: Exit");
        boolean valid = true;
        while (valid) {
            System.out.println("Please input option 1, 2, 3");
            String userChoice1 = scannerObj.nextLine();
            if (!((userChoice1.equals("1")) || (userChoice1.equals("2")) || (userChoice1.equals("3")))) {
                System.out.println("Incorrect input: Please Input 1, 2 or 3");
            }
            else { switch (userChoice1) {
                case "1":
                    System.out.println("Overwrite existing stock number");
                    System.out.println("Please enter the the name of the product you would like to update: ");
                    String productName = scannerObj.nextLine();
                    System.out.println("Please enter the the number of the value you would like to overwrite it with: ");
                    int updateValue = scannerObj.nextInt();
                    updateStock(productName, updateValue);
                    break;
                case "2":
                    System.out.println("Add/subtract from existing stock");
                    break;
                case "3":
                    valid = false;
                    System.out.println("Bye");
            }
            }
        }
    }



    public void updateStock(String productName, int updateValue) {
        //try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select o from Stock o where stockName =:value");

        q.setParameter("value", productName);
        session.getTransaction().commit();

        for (Object i: q.getResultList()) {
            Stock tmp = (Stock) i;
            System.out.println(tmp.getStock());
            tmp.setStock(updateValue);
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









//
    public static void main(String[] args) {
//        updateStockObj.updateStock("action man", 200);

        //        updateStockObj.updateStock();
////        Stock s1 = new Stock(01,"horse","toys",true,10,100, 15);
////        Session session = HibernateUtil.getSessionFactory().openSession();
////        session.beginTransaction();
////        session.save(s1);
////        session.getTransaction().commit();
////        session.close();
//
    }
}
