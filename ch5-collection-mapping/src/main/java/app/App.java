package app;

import entity.Employee;
import entity.VacationEntry;
import repository.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();

        EmployeeRepository employeeRepository = new EmployeeRepository(em);

        // create Employee with VacationEntries and nicknames
        em.getTransaction().begin();
        Employee employee = employeeRepository.createAndSave(
                "John Doe",
                createVacationBookings(),
                createNickNames());
        em.getTransaction().commit();

        // select All Employees
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("Employees: " + employeeList);
    }

    private static Collection<String> createNickNames() {
        return Arrays.asList("Johnny", "Hans", "JoDo");
    }

    private static Collection<VacationEntry> createVacationBookings() {
        return Arrays.asList(
                createVacationEntry(new GregorianCalendar(2019, 10, 31), 5),
                createVacationEntry(new GregorianCalendar(2017, 0, 31), 30),
                createVacationEntry(new GregorianCalendar(2018, 7, 5), 14)
        );
    }

    private static VacationEntry createVacationEntry(Calendar startDate, int daysTaken) {
        return new VacationEntry(startDate, daysTaken);
    }
}
