package ch7.app;

import ch7.data.*;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BulkRunner {
    // todo: DI
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PhoneRepository phoneRepository;

    public BulkRunner(final EntityManager em) {
        employeeRepository = new EmployeeRepository(em);
        departmentRepository = new DepartmentRepository(em);
        phoneRepository = new PhoneRepository(em);
    }

    public void insertDefaultData() {
        Department department = createDefaultDepartment("Dept-1a");
        departmentRepository.createAndSave(department);

        List<Employee> employees = createDefaultEmployees(department);
        employeeRepository.createAndSaveAll(employees);
        department.setEmployees(employees);
    }

    private Department createDefaultDepartment(final String name) {
        final Department department = new Department();
        department.setName(name);
        return department;
    }

    private List<Employee> createDefaultEmployees(final Department department) {
        final List<Employee> employees = Arrays.asList(
                createDefaultEmployee("John", 50000, department),
                createDefaultEmployee("Paul", 65000, department)
        );
        return employees;
    }

    private Employee createDefaultEmployee(final String name, final int salary, final Department department) {
        List<Phone> phones = phoneRepository.createAndSaveAll(createDefaultPhones());
        final Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDepartment(department);
        employee.setPhones(phones);
        return employee;
    }

    private List<Phone> createDefaultPhones() {
        final List<Phone> phones = Arrays.asList(
                createDefaultPhone("Work", createRandomPhoneNumber()),
                createDefaultPhone("Private", createRandomPhoneNumber())
        );
        return phones;
    }

    private Phone createDefaultPhone(final String type, final String randomPhoneNumber) {
        final Phone phone = new Phone();
        phone.setType(type);
        phone.setNumber(randomPhoneNumber);
        return phone;
    }

    private String createRandomPhoneNumber() {
        return IntStream.rangeClosed(0, 9)
                .map(x -> (int) (Math.random() * 10))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }


}
