package ch7.app;

import ch7.entity.Department;
import ch7.entity.Employee;
import ch7.entity.Phone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * inserts:
 * - Employee(name=John, salary=50000,
 *      department=Department(name=Dept-1a),
 *      phones=[Phone(type=Work, number=9637440210), Phone(type=Private, number=4978778799)])
 * - Employee(name=Paul, salary=65000,
 *      department=Department(name=Dept-1a),
 *      phones=[Phone(type=Work, number=7618504390), Phone(type=Private, number=5361294409)])
 * - Employee(name=Paul, salary=65000,
 *      department=Department(name=Dept-2a),
 *      phones=[Phone(type=Work, number=4859551571), Phone(type=Private, number=9955007744)])
 */
final class DbInitializer {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch7EmployeePU");
    private static final EntityManager em = emf.createEntityManager();

    DbInitializer() {
        this.initEmployeeData();
        this.printEmployees();
    }

    private void initEmployeeData() {
        Employee empl1 = new Employee("John", 50000, null, null);
        empl1.setPhones(createDefaultPhones(empl1));
        Employee empl2 = new Employee("Paul", 65000, null, null);
        empl2.setPhones(createDefaultPhones(empl2));
        Employee empl3 = new Employee("Eric", 65000, null, null);
        empl3.setPhones(createDefaultPhones(empl3));

        Department dept1 = new Department("Dept-1a", Arrays.asList(empl1, empl2));
        empl1.setDepartment(dept1);
        empl2.setDepartment(dept1);

        Department dept2 = new Department("Dept-2a", Collections.singletonList(empl3));
        empl3.setDepartment(dept2);

        em.getTransaction().begin();
        em.persist(empl1);
        em.persist(empl2);
        em.persist(empl3);
        em.getTransaction().commit();
        em.clear();
    }

    private static List<Phone> createDefaultPhones(Employee employee) {
        return Arrays.asList(
                new Phone("Work", createRandomPhoneNumber(), employee),
                new Phone("Private", createRandomPhoneNumber(), employee)
        );
    }

    private static String createRandomPhoneNumber() {
        return IntStream.rangeClosed(0, 9)
                .map(x -> (int) (Math.random() * 10))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    private void printEmployees() {
        StringBuilder sb = new StringBuilder("# DbInitializer - data initialized");
        em.createQuery("select e from Employee e").getResultList().stream()
                .forEach(e -> sb.append("\n# " + e));
        System.out.println(sb.toString());
    }
}
