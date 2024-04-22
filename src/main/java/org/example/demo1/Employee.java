package org.example.demo1;

public class Employee implements Comparable<Employee> {

    private String firstName;
    private String lastName;
    private EmployeeCondition condition;
    private int birthYear;
    private double salary;


    public Employee(String firstName, String lastName, EmployeeCondition condition, int birthYear, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    //gettery
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeCondition getCondition() {
        return condition;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public double getSalary() {
        return salary;
    }

    //Settery
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCondition(EmployeeCondition condition) {
        this.condition = condition;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public void printInfo() {
        System.out.println("Imię: " + firstName);
        System.out.println("Nazwisko: " + lastName);
        System.out.println("Stan pracownika: " + condition);
        System.out.println("Rok urodzenia: " + birthYear);
        System.out.println("Wynagrodzenie: " + salary);
        // Można dodać wypisywanie innych pól jeśli istnieją
    }

    // Implementacja metody compareTo z interfejsu Comparable
    @Override
    public int compareTo(Employee otherEmployee) {
        return this.lastName.compareTo(otherEmployee.lastName);
    }
}
