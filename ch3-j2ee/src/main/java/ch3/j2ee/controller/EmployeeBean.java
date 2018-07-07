package ch3.j2ee.controller;

import ch3.j2ee.ejb.EmployeeEJB;
import ch3.j2ee.entity.Employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "blogEntryBean")
@RequestScoped
public class EmployeeBean {

    @Inject
    private EmployeeEJB employeeEJB;

    private Employee employee = new Employee();

    public List<Employee> getEmployees() {
        return employeeEJB.findAll();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String saveEmployee() {
        employeeEJB.save(employee);
        return "success";
    }
}
