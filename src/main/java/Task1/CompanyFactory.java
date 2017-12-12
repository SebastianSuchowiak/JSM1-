package Task1;

import java.util.ArrayList;
import java.util.Random;

class CompanyFactory {

    private ArrayList<Employee> company;
    private int numberOfEmployees;
    private int numberOfManagers;
    private int numberOfDevelopers;
    private final String[] developerRoles = {"Tester", "Developer", "Contributor", "Team Leader"};
    private int maxSubordinates = 6;
    private ArrayList<Integer> limits;

    CompanyFactory(int numberOfManagers, int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
        this.numberOfManagers = numberOfManagers + 1; // CEO is also manager
        numberOfEmployees = numberOfDevelopers + numberOfManagers;
        company = new ArrayList<>();

        this.generateLimits();
    }

    private void generateLimits() {
        limits = new ArrayList<>();
        Random generator = new Random();
        int randomIndex;

        if ((numberOfEmployees / numberOfManagers) + 2 > maxSubordinates) {
            maxSubordinates = numberOfEmployees / numberOfManagers + 2;
        }

        for (int i = 0; i < numberOfManagers; ++i) {
            limits.add(1);
        }

        for (int i = 0; i < (numberOfEmployees - numberOfManagers); ++i) {
            randomIndex = generator.nextInt(numberOfManagers);
            while (limits.get(randomIndex) >= maxSubordinates) {
                randomIndex = generator.nextInt(numberOfManagers);
            }
            limits.set(randomIndex, limits.get(randomIndex) + 1);
        }
    }

    ArrayList<Employee> generateCompany() {
        this.hireSubordinates(this.generateCEO());
        return company;
    }

    private void hireSubordinates(TeamManager manager) {

        int vacancies = manager.vacancies();
        int option;
        Random generator = new Random();

        for (int i = 0; i < vacancies; ++i) {

            option = generator.nextInt(2); // 0 manager, 1 developer
            if ((option == 0 || i == 0) && numberOfManagers > 0) {
                manager.hire(this.generateManager());
                numberOfManagers -= 1;
                numberOfEmployees -= 1;
            } else if ((option == 1 || numberOfManagers == 0) && numberOfDevelopers > 0) {
                manager.hire(this.generateDeveloper());
                numberOfDevelopers -= 1;
                numberOfEmployees -= 1;
            }

        }
        if (numberOfEmployees == 0) {System.out.println("TAK");}
        for (Employee employee: manager.getSubordinates()) {
            if (employee instanceof TeamManager) {
                this.hireSubordinates((TeamManager) employee);
            }
            company.add(employee);
        }
    }

    private TeamManager generateCEO() {

        int i;
        Random generator = new Random();
        TeamManager CEO;

        if (limits.size() > 0) {
            i = limits.remove(generator.nextInt(limits.size()));
        } else {
            i = 0;
        }

        CEO = new TeamManager("CEO",i);
        company.add(CEO);
        return CEO;
    }

    private TeamManager generateManager() {

        int i;
        String role;
        TeamManager manager;
        Random generator = new Random();

        if (limits.size() > 0) {
            i = limits.remove(generator.nextInt(limits.size()));
        } else {
            i = 0;
        }

        if (i > 1) {
            role = "Team Manager";
        } else {
            role = "Personal Manager";
        }

        manager = new TeamManager(role + (numberOfManagers - 1),i);
        return manager;
    }

    private Developer generateDeveloper() {

        String role;
        Random generator = new Random();

        role = developerRoles[generator.nextInt(developerRoles.length)];

        return new Developer(role);
    }

}
