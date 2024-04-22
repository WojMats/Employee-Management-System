package org.example.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    private Map<String, ClassEmployee> employeeGroups;

    public ClassContainer() {
        this.employeeGroups = new HashMap<>();
    }

    public void addClass(String groupName, int maxCapacity) {
        if (!employeeGroups.containsKey(groupName)) {
            employeeGroups.put(groupName, new ClassEmployee(groupName, maxCapacity));
            System.out.println("Dodano nową grupę pracowniczą: " + groupName);
        } else {
            System.out.println("Grupa pracownicza o nazwie " + groupName + " już istnieje.");
        }
    }

    public void removeClass(String groupName) {
        if (employeeGroups.containsKey(groupName)) {
            employeeGroups.remove(groupName);
            System.out.println("Usunięto grupę pracowniczą: " + groupName);
        } else {
            System.out.println("Grupa pracownicza o nazwie " + groupName + " nie istnieje.");
        }
    }

    public List<String> findEmpty() {
        List<String> emptyGroups = new ArrayList<>();
        for (Map.Entry<String, ClassEmployee> entry : employeeGroups.entrySet()) {
            if (entry.getValue().getEmployees().isEmpty()) {
                emptyGroups.add(entry.getKey());
            }
        }
        return emptyGroups;
    }

    public void summary() {
        for (Map.Entry<String, ClassEmployee> entry : employeeGroups.entrySet()) {
            String groupName = entry.getKey();
            ClassEmployee group = entry.getValue();
            double fillPercentage = (double) group.getEmployees().size() / group.getMaxCapacity() * 100;
            System.out.println("Grupa pracownicza: " + groupName + ", Procentowe zapełnienie: " + fillPercentage + "%");
        }
    }



    public ClassEmployee getClass(String groupName) {
        return employeeGroups.get(groupName);
    }

    public boolean containsGroup(String groupName) {
        return employeeGroups.containsKey(groupName);
    }

    public List<ClassEmployee> getAllGroups() {
        return new ArrayList<>(employeeGroups.values());
    }

    public int getTotalEmployees() {
        int totalEmployees = 0;
        for (ClassEmployee group : employeeGroups.values()) {
            totalEmployees += group.getEmployees().size();
        }
        return totalEmployees;
    }
}
