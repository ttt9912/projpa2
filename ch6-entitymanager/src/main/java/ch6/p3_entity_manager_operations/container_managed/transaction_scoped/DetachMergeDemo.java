package ch6.p3_entity_manager_operations.container_managed.transaction_scoped;

import ch6.entities.Employee;
import ch6.entities.merge_demo.*;
import ch6.p3_entity_manager_operations.container_managed.transaction_scoped.config.P3TxScopedConfig;
import ch6.util.jdbc.JdbcUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Random;

@Component
public class DetachMergeDemo {
    public static final JdbcUtil jdbcUtil = new JdbcUtil("jdbc:h2:mem:testdb", "sa", "");
    private final long dummyId = new Random().nextLong();

    private DetachMergeDemo self;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void initContext() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(P3TxScopedConfig.class);
        self = ctx.getBean(DetachMergeDemo.class);
    }

    @Test
    public void detachedTest() {
        self.detached();
    }

    @Transactional
    public void detached() {
        Employee employee = new Employee(1L, "Paul");
        employee.setName("John");

        Employee managedEmployee = em.merge(employee); // employee: not managed, managedEmployee: managed
        System.out.println(em.find(Employee.class, 1L)); // John
        System.out.println(em.contains(employee)); // false
        System.out.println(em.contains(managedEmployee)); // true

        employee.setName("Ringo"); // not persisted
        System.out.println(em.find(Employee.class, 1L)); // John

        managedEmployee.setName("Ringo"); // persisted
        System.out.println(em.find(Employee.class, 1L)); // Ringo
    }

    @Test
    public void mergeRelationshipsTest() {
        self.mergeRelationshipsSetup();
        self.mergeRelationships();
        self.result();
    }

    @Transactional
    public void mergeRelationshipsSetup() {
        DepartmentC dept = new DepartmentC(30, "A");
        em.persist(dept);
    }

    @Transactional
    public void mergeRelationships() {
        // persistence context
        AddressC addr1 = new AddressC(100, "NY");
        EmployeeC emp1 = new EmployeeC(10, "John", addr1, null, null, null);
        PhoneC phone1 = new PhoneC(20, "555-222", null);
        em.persist(addr1);
        em.persist(emp1);
        em.persist(phone1);
        em.flush();
        result();

        // create offline stuff
        AddressC addr = new AddressC(100, "AT");
        DepartmentC dept = new DepartmentC(30, "B");
        BadgeC badge = new BadgeC(77);
        EmployeeC emp = new EmployeeC(10, "Paul", addr, dept, Collections.singletonList(phone1), Collections.singletonList(badge));
        phone1.setEmployee(emp);

        System.out.println(em.contains(addr1)); // true
        System.out.println(em.contains(emp1)); // true
        System.out.println(em.contains(phone1)); // true
        System.out.println(em.contains(addr)); // false
        System.out.println(em.contains(emp)); // false
        System.out.println(em.contains(dept)); // false
        System.out.println(em.contains(badge)); // false

        // merge
        em.merge(emp);
    }

    @Transactional
    public void result() {
        EmployeeC employeeC = em.find(EmployeeC.class, 10L);
        System.out.println(employeeC);
    }

}
