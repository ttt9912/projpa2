package ch7.app;

import ch7.entity.*;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class DefaultDataCreator {
    // todo: DI
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PhoneRepository phoneRepository;

    DefaultDataCreator(final EntityManager em) {
        employeeRepository = new EmployeeRepository(em);
        departmentRepository = new DepartmentRepository(em);
        phoneRepository = new PhoneRepository(em);
    }

    void insertDefaultData() {
        Department dept1 = createDefaultDepartment("Dept-1a");
        Department dept2 = createDefaultDepartment("Dept-2a");
        departmentRepository.createAndSaveAll(dept1, dept2);

        Employee empl1 = createDefaultEmployee("John", 50000, dept1);
        Employee empl2 = createDefaultEmployee("Paul", 65000, dept1);
        Employee empl3 = createDefaultEmployee("Keith", 100000, dept2);
        employeeRepository.createAndSaveAll(empl1, empl2);

        dept1.setEmployees(Arrays.asList(empl1, empl2));
        dept2.setEmployees(Collections.singletonList(empl3));
    }

    private Department createDefaultDepartment(final String name) {
        final Department department = new Department();
        department.setName(name);
        return department;
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
