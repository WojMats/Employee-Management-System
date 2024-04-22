package org.example.demo1;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        //kontener grup
        ClassContainer container = new ClassContainer();

        container.addClass("Grupa A", 5);
        container.addClass("Grupa B", 10);
        container.addClass("Grupa C", 8);

        Employee employee1 = new Employee("Jan", "Kowalski", EmployeeCondition.OBECNY, 1985, 5000);
        Employee employee2 = new Employee("Anna", "Nowak", EmployeeCondition.DELEGACJA, 1990, 5500);
        Employee employee3 = new Employee("Piotr", "Wiśniewski", EmployeeCondition.CHORY, 1982, 4800);
        Employee employee4 = new Employee("Katarzyna", "Dąbrowska", EmployeeCondition.OBECNY, 1978, 6000);
        Employee employee5 = new Employee("Michał", "Lewandowski", EmployeeCondition.NIEOBECNY, 1995, 5200);
        Employee employee6 = new Employee("Agnieszka", "Wójcik", EmployeeCondition.OBECNY, 1988, 5300);
        Employee employee7 = new Employee("Krzysztof", "Kamiński", EmployeeCondition.DELEGACJA, 1983, 5700);
        Employee employee8 = new Employee("Małgorzata", "Kowalczyk", EmployeeCondition.OBECNY, 1975, 6200);
        Employee employee9 = new Employee("Adam", "Zieliński", EmployeeCondition.NIEOBECNY, 1993, 5100);


        container.getClass("Grupa A").addEmployee(employee1);
        container.getClass("Grupa A").addEmployee(employee2);
        container.getClass("Grupa A").addEmployee(employee3);
        container.getClass("Grupa B").addEmployee(employee4);
        container.getClass("Grupa B").addEmployee(employee5);
        container.getClass("Grupa C").addEmployee(employee6);
        container.getClass("Grupa C").addEmployee(employee7);
        container.getClass("Grupa C").addEmployee(employee8);

        // info o grupach pracowniczych
        container.summary();

        // Usuwanie
        container.getClass("Grupa A").removeEmployee(employee3);

        // Zmiana stanu
        container.getClass("Grupa C").changeCondition(employee6, EmployeeCondition.CHORY);

        // Dodawanie wynagrodzenia
        container.getClass("Grupa B").addSalary(employee4, 500);

        // Wyszukiwanie pracownika po nazwisku
        Employee foundEmployee = container.getClass("Grupa A").search("Nowak");
        if (foundEmployee != null) {
            System.out.println("Znaleziono pracownika: " + foundEmployee.getFirstName() + " " + foundEmployee.getLastName());
        } else {
            System.out.println("Nie znaleziono pracownika.");
        }


        // Wyszukiwanie grup pracowniczych, które są puste
        List<String> emptyGroups = container.findEmpty();
        System.out.println("Puste grupy:");
        for (String groupName : emptyGroups) {
            System.out.println(groupName);
        }


        List<Employee> matchingEmployees = container.getClass("Grupa B").searchPartial("Lew");
        for (Employee employee : matchingEmployees) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
        }



        // Sortowanie pracowników w grupie po wynagrodzeniu
        List<Employee> sortedBySalary = container.getClass("Grupa B").sortBySalary();
        System.out.println("Pracownicy w Grupie B posortowani po wynagrodzeniu:");
        for (Employee employee : sortedBySalary) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName() + ": " + employee.getSalary());
        }

        // Znalezienie pracownika o najwyższym wynagrodzeniu
        Employee highestPaidEmployee = container.getClass("Grupa C").max();
        System.out.println("Pracownik o najwyższym wynagrodzeniu w Grupie C: " + highestPaidEmployee.getFirstName() + " " + highestPaidEmployee.getLastName() + ": " + highestPaidEmployee.getSalary());



        // Porównanie pracowników
        int result = employee1.compareTo(employee2);
        if (result < 0) {
            System.out.println(employee1.getLastName() + " jest przed " + employee2.getLastName() + " w porządku alfabetycznym.");
        } else if (result > 0) {
            System.out.println(employee2.getLastName() + " jest przed " + employee1.getLastName() + " w porządku alfabetycznym.");
        } else {
            System.out.println("Nazwiska pracowników są takie same.");
        }

        // Wypisanie informacji o pracownikach
//        employee1.printInfo();
//        System.out.println();
//        employee2.printInfo();
//        System.out.println();
//        employee3.printInfo();
//        System.out.println();
//        employee4.printInfo();
//        System.out.println();
//            // Usuwanie pracownika z grupy
//            container.getClass("Grupa A").removeEmployee(employee1);
//
//            // Zmiana stanu pracownika
//            container.getClass("Grupa B").changeCondition(employee4, EmployeeCondition.OBECNY);
//
//            // Dodawanie wynagrodzenia pracownikowi
//            container.getClass("Grupa B").addSalary(employee3, 1000);


//            ClassEmployee group = new ClassEmployee("Nazwa grupy", 10);
//
//            Employee emp1 = new Employee("Jan", "Kowalski", EmployeeCondition.OBECNY, 1985, 5000);
//            Employee emp2 = new Employee("Anna", "Nowak", EmployeeCondition.OBECNY, 1990, 5500);
//            group.addEmployee(emp1);
//            group.addEmployee(emp2);
//
//            // Wywołujemy metodę summary()
//            group.summary();

        // Podsumowanie grup pracowniczych
        container.summary();


    }
}
