package ch7.app;

import ch7.data.BulkRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ch7App {

    public static void main(String[] arg) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();

        new BulkRunner(em).insertDefaultData();
    }
}
