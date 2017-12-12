package Task1;

public interface Manager extends Employee {

    void hire(Employee employee);
    void fire(Employee employee);

    boolean canHire();
    int vacancies();

}
