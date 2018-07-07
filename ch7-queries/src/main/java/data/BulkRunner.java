package data;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public final class BulkRunner {

    // todo: DI
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public BulkRunner(final EntityManager em) {
        employeeRepository = new EmployeeRepository(em);
        departmentRepository = new DepartmentRepository(em);
    }

    public void insertDefaultData() {
        Department department = createDefaultDepartment("Dept-1a");
        departmentRepository.createAndSave(department);

        List<Employee> employees = createDefaultEmployees(department);
        employeeRepository.createAndSaveAll(employees);

        department.setEmployees(employees);
    }

    private List<Employee> createDefaultEmployees(final Department department) {
        final List<Employee> employees = Arrays.asList(
                createDefaultEmployee("John", department),
                createDefaultEmployee("Paul", department)
        );
        return employees;
    }

    private Employee createDefaultEmployee(final String name, final Department department) {
        final Employee employee = new Employee();
        employee.setName(name);
        employee.setDepartment(department);
        return employee;
    }

    private Department createDefaultDepartment(final String name) {
        final Department department = new Department();
        department.setName(name);
        return department;
    }
}
