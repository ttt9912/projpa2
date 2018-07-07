package ch5.app;

import ch5.embeddable.Color;
import ch5.embeddable.VacationEntry;
import ch5.entity.*;
import ch5.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class App {

    public static void main(String[] arg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager em = emf.createEntityManager();

        // create and save Employee with VacationEntries (embeddable) and nicknames (basic type)
        EmployeeRepository employeeRepository = new EmployeeRepository(em);
        DepartmentRepository departmentRepository = new DepartmentRepository(em);


        System.out.println("\n--- Element Collections ---");

        em.getTransaction().begin();
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setVacationBookings(createDefaultVacationEntries());
        employee.setNickNames(createDefaultNickNames());
        employeeRepository.createAndSave(employee); // employee is managed from now
        em.getTransaction().commit();

        // ordering collections (only works with reload from db in a new app_n run)
        PrinterRepository printerRepository = new PrinterRepository(em);
        PrinterQueueRepository printerQueueRepository = new PrinterQueueRepository(em);
        PrintJobRepository printJobRepository = new PrintJobRepository(em);


        System.out.println("\n--- Ordering ---");

        em.getTransaction().begin();
        Printer printer = new Printer();
        List<PrinterQueue> printerQueues = printerQueueRepository.createAndSaveAll(createDefaultPrinterQueues(printer));
        printer.setPrinterQueues(printerQueues);
        printer.setColors(createDefaultColors());
        printerRepository.createAndSave(printer);

        List<Printer> allPrinters = printerRepository.findAll(Printer.class);
        System.out.println(allPrinters);
        System.out.println(allPrinters.get(0).getPrinterQueues());
        em.getTransaction().commit();

        // persistent ordering (basic type key)
        em.getTransaction().begin();
        PrinterQueue printerQueue = new PrinterQueue();
        printerQueueRepository.createAndSave(printerQueue);
        List<PrintJob> printJobs = createDefaultPrintJobs();
        printJobRepository.createAndSaveAll(printJobs);
        printerQueue.setPrintJobs(printJobs);
        em.getTransaction().commit();

        System.out.println(printerQueue);


        System.out.println("\n--- Maps ---");

        // Maps with basic type key (element collection); value = basic type
        Map<String, String> phoneNumbers = createPhoneNumbersMap();
        em.getTransaction().begin();
        employee.setPhoneNumbers(phoneNumbers);
        em.getTransaction().commit();

        List<Employee> allEmployees = employeeRepository.findAll(Employee.class);
        System.out.println(allEmployees);

        List<Department> allDepartments = departmentRepository.findAll(Department.class);
        System.out.println(allDepartments);


        // Map with basic type key (OneToMany relationship); value = entity_n
//        em.getTransaction().begin();
//        Employee emp1 = new Employee();
//        emp1.setName("John");
//        Employee emp2 = new Employee();
//        emp2.setName("Paul");
//        employeeRepository.createAndSaveAll(Arrays.asList(emp1, emp2));
//        Department departments = new Department();
//        Map<String, Employee> employeesByCubicle = new HashMap<>();
//        employeesByCubicle.put("A1", emp1);
//        employeesByCubicle.put("B5", emp2);
//        departments.setEmployeesByCubicle(employeesByCubicle);
//        departmentRepository.createAndSave(departments);
//        em.getTransaction().commit();
//        System.out.println(departments);

        // Map with basic type key (ManyToMany relationship); value = entity_n
        em.getTransaction().begin();

        Employee emp1 = new Employee("John");
        Employee emp2 = new Employee("Paul");
        employeeRepository.createAndSaveAll(Arrays.asList(emp1, emp2));

        Department dept1 = new Department("Dept_1");
        Department dept2 = new Department("Dept_2");
        departmentRepository.createAndSaveAll(Arrays.asList(dept1, dept2));

        Map<String, Employee> employeesByCubicle = new HashMap<>();
        employeesByCubicle.put("A1", emp1);
        employeesByCubicle.put("B5", emp2);
        dept1.setEmployeesByCubicle(employeesByCubicle);

        emp1.setDepartments(Arrays.asList(dept1, dept2));
        emp2.setDepartments(Arrays.asList(dept1, dept2));

        em.getTransaction().commit();
        System.out.println("Employee1: " + emp1);
        System.out.println("Employee2: " + emp2);
        System.out.println("Department1: " + dept1);
        System.out.println("Department2: " + dept2);

        // Map with Entity key
        em.getTransaction().begin();

        Employee empl1 = new Employee("John");
        Employee empl2 = new Employee("Paul");
        employeeRepository.createAndSaveAll(Arrays.asList(empl1, empl2));

        Map<Employee, Integer> employeesWithSeniority = new HashMap<>();
        employeesWithSeniority.put(empl1, 2);
        employeesWithSeniority.put(empl2, 3);

        Department department = departmentRepository.createAndSave(new Department("Department A"));
        department.setEmployeesWithSeniority(employeesWithSeniority);

        em.getTransaction().commit();

        departmentRepository.findAll(Department.class).stream()
                .forEach(d -> System.out.println("Department: " + d.getDepartmentName()
                        + ", Employees by Seniority: " + d.getEmployeesWithSeniority()));
    }

    private static Map<String, String> createPhoneNumbersMap() {
        final Map<String, String> phoneNumbers = new HashMap<>();
        phoneNumbers.put("Home", "0123456789");
        phoneNumbers.put("Mobile", "789456456");
        phoneNumbers.put("Work", "456789123");
        return phoneNumbers;
    }

    private static Collection<String> createDefaultNickNames() {
        return Arrays.asList("Johnny", "Hans", "JoDo");
    }

    private static Collection<VacationEntry> createDefaultVacationEntries() {
        return Arrays.asList(
                createVacationEntry(new GregorianCalendar(2019, 10, 31), 5),
                createVacationEntry(new GregorianCalendar(2017, 0, 31), 30),
                createVacationEntry(new GregorianCalendar(2018, 7, 5), 14)
        );
    }

    private static VacationEntry createVacationEntry(Calendar startDate, int daysTaken) {
        return new VacationEntry(startDate, daysTaken);
    }

    private static List<PrinterQueue> createDefaultPrinterQueues(final Printer printer) {
        final List<PrinterQueue> printerQueues = new ArrayList<>();
        printerQueues.add(new PrinterQueue(5, printer));
        printerQueues.add(new PrinterQueue(10, printer));
        printerQueues.add(new PrinterQueue(2, printer));
        return printerQueues;
    }

    private static List<PrintJob> createDefaultPrintJobs() {
        final List<PrintJob> printJobs = new ArrayList<>();
        printJobs.add(new PrintJob("job-2"));
        printJobs.add(new PrintJob("job-3"));
        printJobs.add(new PrintJob("job-1"));
        return printJobs;
    }

    private static List<Color> createDefaultColors() {
        final List<Color> colors = new ArrayList<>();
        colors.add(new Color("Lightgrey"));
        colors.add(new Color("Red"));
        colors.add(new Color("Green"));
        return colors;
    }


}
