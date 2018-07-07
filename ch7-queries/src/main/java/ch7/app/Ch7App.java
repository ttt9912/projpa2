package ch7.app;

import javax.persistence.*;

public class Ch7App {

    public static void main(String[] arg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();
        new BulkRunner(em).insertDefaultData();

        /*
         * JPQL
         */
        // Query: untyped
        Query query = em.createQuery("SELECT e.name FROM  Employee e");
        System.out.println(query.getResultList());

        // TypedQuery: typesafe
        TypedQuery<String> query1 = em.createQuery("SELECT e.name FROM  Employee e", String.class);
        System.out.println(query1.getResultList());

    }
}
