package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;
import java.util.Optional;

public class Service {
    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableView<ClassEmployee> classEmployeeTableView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button modifyDataButton;

    @FXML
    private Button sortButton;

    @FXML
    private TextField searchTextfield;

    private ClassContainer container;

    public Service() {
        // Inicjalizacja kontenera grup
        container = new ClassContainer();

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

    }

    @FXML
    protected void initialize() {
        // Inicjalizacja widoku tabeli pracowników
        TableColumn<Employee, String> firstNameCol = new TableColumn<>("Imię");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Nazwisko");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee, EmployeeCondition> conditionCol = new TableColumn<>("Stan");
        conditionCol.setCellValueFactory(new PropertyValueFactory<>("condition"));

        TableColumn<Employee, Integer> birthYearCol = new TableColumn<>("Rok urodzenia");
        birthYearCol.setCellValueFactory(new PropertyValueFactory<>("birthYear"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Wynagrodzenie");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTableView.getColumns().addAll(firstNameCol, lastNameCol, conditionCol, birthYearCol, salaryCol);

        // Inicjalizacja widoku tabeli grup pracowniczych
        TableColumn<ClassEmployee, String> groupNameCol = new TableColumn<>("Nazwa grupy");
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<ClassEmployee, Integer> maxCapacityCol = new TableColumn<>("Maksymalna pojemność");
        maxCapacityCol.setCellValueFactory(new PropertyValueFactory<>("maxCapacity"));

        classEmployeeTableView.getColumns().addAll(groupNameCol, maxCapacityCol);

        // Wywołanie metody odświeżającej tabelę pracowników
        refreshEmployeeTable();


        refreshClassEmployeeTable();
    }

    @FXML
    protected void onAddEmployeeButtonClick() {
        // Implementacja dodawania nowego pracownika

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dodaj pracownika");
        dialog.setHeaderText(null);
        dialog.setContentText("Wprowadź dane pracownika (imię, nazwisko, stan, rok urodzenia, wynagrodzenie):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String input = result.get();
            String[] data = input.split(",");
            if (data.length == 5) {
                String firstName = data[0].trim();
                String lastName = data[1].trim();
                EmployeeCondition condition = EmployeeCondition.valueOf(data[2].trim().toUpperCase());
                int birthYear = Integer.parseInt(data[3].trim());
                double salary = Double.parseDouble(data[4].trim());

                Employee newEmployee = new Employee(firstName, lastName, condition, birthYear, salary);
                // Dodadanie pracownika do wybranej grupy

                ChoiceDialog<ClassEmployee> groupDialog = new ChoiceDialog<>(null, container.getAllGroups());
                groupDialog.setTitle("Wybierz grupę");
                groupDialog.setHeaderText(null);
                groupDialog.setContentText("Wybierz grupę, do której chcesz dodać pracowownika");
                Optional<ClassEmployee> selectedGroup = groupDialog.showAndWait();
                if (selectedGroup.isPresent()) {
                    ClassEmployee group = selectedGroup.get();
                    group.addEmployee(newEmployee);
                    refreshEmployeeTable();
                    showAlert("Pracownik został dodany do grupy " + group.getGroupName());
                }
            } else {
                showAlert("Nieprawidłowe dane pracownika. Wprowadź dane w formacie: imię, nazwisko, stan, rok urodzenia, wynagrodzenie");
            }
        }
    }

    @FXML
    protected void onAddGroupButtonClick() {
// Implementacja dodawania nowej grupy pracowniczej
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Dodaj grupę");
        dialog.setHeaderText(null);
        dialog.setContentText("Wprowadź nazwę grupy i maksymalną pojemność (oddzielone przecinkiem):");


        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String input = result.get();
            String[] data = input.split(",");
            if (data.length == 2) {
                String groupName = data[0].trim();
                int maxCapacity = Integer.parseInt(data[1].trim());

                container.addClass(groupName, maxCapacity);
                refreshClassEmployeeTable();
                showAlert("Grupa " + groupName + " została dodana.");
            } else {
                showAlert("Nieprawidłowe dane grupy. Wprowadź dane w formacie: nazwa grupy, maksymalna pojemność");
            }
        }
    }


    @FXML
    protected void onDeleteButtonClick() {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            for (ClassEmployee group : container.getAllGroups()) {
                if (group.removeEmployee(selectedEmployee)) {
                    refreshEmployeeTable();
                    showAlert("Pracownik został usunięty.");
                    return;
                }
            }
        }
        showAlert("Nie wybrano pracownika do usunięcia.");
    }



    @FXML
    protected void onModifyDataButtonClick() {
        // Sprawdź, czy wybrano pracownika
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            // Otwórz okno dialogowe do modyfikacji danych pracownika
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modyfikuj dane pracownika");
            dialog.setHeaderText(null);
            dialog.setContentText("Wprowadź nowe dane pracownika (imię, nazwisko, stan, rok urodzenia, wynagrodzenie):");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String input = result.get();
                String[] data = input.split(",");
                if (data.length == 5) {
                    String firstName = data[0].trim();
                    String lastName = data[1].trim();
                    EmployeeCondition condition = EmployeeCondition.valueOf(data[2].trim().toUpperCase());
                    int birthYear = Integer.parseInt(data[3].trim());
                    double salary = Double.parseDouble(data[4].trim());

                    // Zaktualizuj dane pracownika
                    selectedEmployee.setFirstName(firstName);
                    selectedEmployee.setLastName(lastName);
                    selectedEmployee.setCondition(condition);
                    selectedEmployee.setBirthYear(birthYear);
                    selectedEmployee.setSalary(salary);

                    // Odśwież tabelę pracowników
                    employeeTableView.refresh();

                    showAlert("Dane pracownika zostały zmodyfikowane.");
                } else {
                    showAlert("Nieprawidłowe dane pracownika. Wprowadź dane w formacie: imię, nazwisko, stan, rok urodzenia, wynagrodzenie");
                }
            }
        } else {
            // Sprawdź, czy wybrano grupę pracowniczą
            ClassEmployee selectedGroup = classEmployeeTableView.getSelectionModel().getSelectedItem();
            if (selectedGroup != null) {
                // Otwórz okno dialogowe do modyfikacji danych grupy
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Modyfikuj dane grupy");
                dialog.setHeaderText(null);
                dialog.setContentText("Wprowadź nową nazwę grupy i maksymalną pojemność (oddzielone przecinkiem):");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String input = result.get();
                    String[] data = input.split(",");
                    if (data.length == 2) {
                        String groupName = data[0].trim();
                        int maxCapacity = Integer.parseInt(data[1].trim());

                        // Zaktualizuj dane grupy
                        selectedGroup.setGroupName(groupName);
                        selectedGroup.setMaxCapacity(maxCapacity);

                        // Odśwież tabelę grup pracowniczych
                        classEmployeeTableView.refresh();

                        showAlert("Dane grupy zostały zmodyfikowane.");
                    } else {
                        showAlert("Nieprawidłowe dane grupy. Wprowadź dane w formacie: nazwa grupy, maksymalna pojemność");
                    }
                }
            } else {
                showAlert("Nie wybrano pracownika ani grupy do modyfikacji.");
            }
        }
    }

    @FXML
    protected void onSortButtonClick() {
        ObservableList<Employee> employees = employeeTableView.getItems();
        FXCollections.sort(employees, Comparator.comparing(Employee::getLastName));
        employeeTableView.setItems(employees);
    }

    @FXML
    protected void onSearchTextChanged() {
        // Pobierz tekst z pola wyszukiwania, usuń białe znaki i zmień na małe litery
        String searchText = searchTextfield.getText().trim().toLowerCase();

        // Sprawdź, czy pole wyszukiwania nie jest puste
        if (!searchText.isEmpty()) {
            // Utwórz listę, która będzie przechowywać pracowników pasujących do wyszukiwanego tekstu
            ObservableList<Employee> filteredEmployees = FXCollections.observableArrayList();

            // Przejdź przez wszystkie grupy pracownicze
            for (ClassEmployee group : container.getAllGroups()) {
                // Przejdź przez wszystkich pracowników w danej grupie
                for (Employee employee : group.getEmployees()) {
                    // Sprawdź, czy imię lub nazwisko pracownika zawiera wpisany tekst (ignoruj wielkość liter)
                    if (employee.getLastName().toLowerCase().contains(searchText) ||
                            employee.getFirstName().toLowerCase().contains(searchText)) {
                        // Jeśli tak, dodaj pracownika do listy przefiltrowanych pracowników
                        filteredEmployees.add(employee);
                    }
                }
            }

            // Ustaw przefiltrowaną listę pracowników w tabeli
            employeeTableView.setItems(filteredEmployees);
        } else {
            // Jeśli pole wyszukiwania jest puste, odśwież listę pracowników w tabeli
            refreshEmployeeTable();
        }
    }




    // Metoda do odświeżania tabeli pracowników
    private void refreshEmployeeTable() {
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        for (ClassEmployee group : container.getAllGroups()) {
            allEmployees.addAll(group.getEmployees());
        }
        employeeTableView.setItems(allEmployees);
    }

    // Metoda do odświeżania tabeli grup pracowniczych
    private void refreshClassEmployeeTable() {
        ObservableList<ClassEmployee> allGroups = FXCollections.observableArrayList(container.getAllGroups());
        classEmployeeTableView.setItems(allGroups);
    }

    // Metoda do wyświetlania alertów
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
