package ch6.p3_entity_manager_operations.application_managed;

import ch6.entities.Address;
import ch6.entities.ParkingSpace;
import ch6.entities.cascade_demo.EmployeeWithRelationships;
import ch6.entities.cascade_demo.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class FlushDemo {
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void init() {
        emf = Persistence.createEntityManagerFactory("ch6EmployeePU");
        em = emf.createEntityManager();
    }

    @Test
    public void flushDetachedEntities() {
        em.getTransaction().begin();

        EmployeeWithRelationships employee = new EmployeeWithRelationships(1L);
        em.persist(employee);

        ParkingSpace ps = new ParkingSpace();
        em.persist(ps);

        Phone phone = new Phone();
        phone.setEmployee(employee);

        Address addr = new Address();
        em.persist(addr);

        employee.setParkingSpace(ps);
        employee.setAdress(addr);
        employee.setPhones(Arrays.asList(phone));
        em.getTransaction().commit();

        em.remove(addr);

        ps.setNumber("34"); // managed
        phone.setNumber("555-555"); // new
        addr.setCity("NY"); // detached

        em.getTransaction().begin();
        em.flush();
        em.clear();
        em.getTransaction().commit();

        System.out.println(em.find(EmployeeWithRelationships.class, 1L));
    }
}
