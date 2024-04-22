package org.example.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassEmployee {
    private String groupName;
    private List<Employee> employees;
    private int maxCapacity;

    public ClassEmployee(String groupName, int maxCapacity) {
        this.groupName = groupName;
        this.maxCapacity = maxCapacity;
        this.employees = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Pracownik " + employee.getFirstName() + " " + employee.getLastName() + " już istnieje w grupie.");
            return;
        }

        if (employees.size() >= maxCapacity) {
            System.out.println("Grupa pracownicza osiągnęła maksymalną liczbę pracowników.");
            return;
        }

        employees.add(employee);
    }

    public void addSalary(Employee employee, double amount) {
        employee.setSalary(employee.getSalary() + amount);
    }

    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }

    public void changeCondition(Employee employee, EmployeeCondition condition) {
        employee.setCondition(condition);
    }

    public Employee search(String lastName) {
        // Sortujemy pracowników według nazwiska
        List<Employee> sortedEmployees = sortByName();

        // Tworzymy obiekt Employee z podanym nazwiskiem w celu wyszukania
        Employee searchEmployee = new Employee("", lastName, null, 0, 0); //
        // Szukamy pracownika za pomocą binarySearch
        int index = Collections.binarySearch(sortedEmployees, searchEmployee, Comparator.comparing(Employee::getLastName));

        // Jeśli indeks jest większy lub równy zero, znaleziono pracownika
        if (index >= 0) {
            return sortedEmployees.get(index);
        } else {
            return null;
        }
    }

    public List<Employee> searchPartial(String partialName) {
        List<Employee> matchingEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getLastName().contains(partialName) || employee.getFirstName().contains(partialName)) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }

    public int countByCondition(EmployeeCondition condition) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee.getCondition() == condition) {
                count++;
            }
        }
        return count;
    }

    public void summary() {
        for (Employee employee : employees) {
            employee.printInfo();
            System.out.println();
        }
    }

    public List<Employee> sortByName() {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        Collections.sort(sortedEmployees, Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName));
        return sortedEmployees;
    }

    public List<Employee> sortBySalary() {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        Collections.sort(sortedEmployees, (emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary()));
        return sortedEmployees;
    }

    public Employee max() {
        return Collections.max(employees, Comparator.comparing(Employee::getSalary));
    }
}
