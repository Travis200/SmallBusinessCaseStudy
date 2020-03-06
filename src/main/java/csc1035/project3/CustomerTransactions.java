package csc1035.project3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import javax.management.Query;

public class CustomerTransactions {

    public static void transaction() {
        List<String> items = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        String item;
        int quantity;
        List<String> itemNames = new ArrayList<>();
        List<Integer> itemPrices = new ArrayList<>();
        do {
            System.out.println("What item would you like to add to the transaction followed by the quantity with a space in between");
            System.out.println("or type false to end it");
            String input1 = scanner.nextLine();
            Scanner scanner2 = new Scanner(input1).useDelimiter("\\s");
            item = (scanner2.next());
            if (item.matches("false")){
                break;
            }
            quantity = (scanner2.nextInt());
            scanner2.close();


            try {
                for (int i = 0; i < quantity; i++) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Query q = session.createQuery("select o from Stock o where stockName =:value");
                    q.setParameter("value", item);
                    session.getTransaction().commit();
                    if (q.getResultList().size()==0){
                        System.out.println("Item does not exist");
                        break;
                    }
                    for (Object j : q.getResultList()) {
                        session = HibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();
                        Stock tmp = (Stock) j;

                        if (tmp.getStock() <= 0) {
                            System.out.println();
                            System.out.println("Out of Stock : " + tmp.getStockName());
                            break;
                        } else if(tmp.getStock()>0) {
                            tmp.setStock(tmp.getStock() - 1);
                            session.update(tmp);
                            session.getTransaction().commit();
                            itemNames.add(tmp.getStockName());
                            itemPrices.add(tmp.getSellPrice());
                            System.out.println("Item added to transaction");
                            break;
                        }

                    }
                }

            } catch (HibernateException e) {
            } finally {
                session.close();
            }

        }
        while (!item.matches("false"));
        {
            if(itemNames.size()>0) {
                Receipt.AsciiTable(itemNames, itemPrices);
            }
        }
    }
}