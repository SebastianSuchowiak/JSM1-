package Task1;

import java.util.ArrayList;
import java.util.Comparator;

public class TeamManager extends AbstractEmployee implements Manager {


    private ArrayList<Employee> subordinates;
    private int subordinatesLimit;


    TeamManager(String role, int subordinatesLimit) {
        super(role, "Manager");

        if (subordinatesLimit < 0) {
            throw new IllegalArgumentException();
        }

        this.subordinatesLimit = subordinatesLimit;
        this.subordinates = new ArrayList<>();
        this.subordinates.ensureCapacity(subordinatesLimit);
    }

    @Override
    public void hire(Employee employee) {
        if (this.canHire()) {
            if (!subordinates.contains(employee)) {
                subordinates.add(employee);
                System.out.println("New " + employee.getRole() + " successfully hired by " + this.getRole());
            } else {
                System.out.println("Given employee is already hired by this " + this.getRole());
            }
        } else {
            System.out.println(this.getRole() + " can not hire more employees");
        }
    }

    @Override
    public void fire(Employee employee) {
        if (subordinates.contains(employee)) {
            subordinates.remove(subordinates.indexOf(employee));
            System.out.println(employee.getRole() + " successfully fired by " + this.getRole());
        } else {
            System.out.println("Given employee is not hired by this " + this.getRole());
        }
    }

    @Override
    public boolean canHire() {
        return subordinates.size() < subordinatesLimit;
    }

    @Override
    public int vacancies() {
        return subordinatesLimit - subordinates.size();
    }

    @Override
    public Report reportWork() {

        Report report = new Report();

        for (Employee subordinate: subordinates) {
            report = report.combine(subordinate.reportWork());
        }

        return report;

    }

    @Override
    public void assignTask(Task task) {

        ArrayList<Developer> developers = this.listSubservientDevelopers();

        if (developers.isEmpty()) {
            throw new IllegalArgumentException("Manager has no subservient Developers");
        } else {
            developers.sort(Comparator.comparing(Developer::getAllWork));
            developers.get(0).assignTask(task);
        }
    }

    public void assignTask(Task task, Employee employee) {
        if (subordinates.contains(employee)) {
            employee.assignTask(task);
        }
        else {
            throw new IllegalArgumentException("Given employee is not subservient to this manager");
        }
    }

    private ArrayList<Developer> listSubservientDevelopers() {

        ArrayList<Developer> developers = new ArrayList<>();

        for (Employee employee: subordinates) {
            if (employee instanceof Developer) {
                developers.add((Developer) employee);
            } else {
                developers.addAll(((TeamManager) employee).listSubservientDevelopers());
            }
        }

        return developers;
    }

    @Override
    public String toString() {
        return super.toString()
                + "[subordinatesLimit=" + subordinatesLimit
                + ", subordinates=" + subordinates
                + "]";
    }

    public ArrayList<Employee> getSubordinates() {
        return subordinates;
    }
}
