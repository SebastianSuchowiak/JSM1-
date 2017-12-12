package Task1;


import java.util.ArrayList;

class Developer extends AbstractEmployee {

    private ArrayList<Task> tasks = new ArrayList<>();

    Developer(String role) {
        super(role, "Developer");
    }

    @Override
    public Report reportWork() {

        ArrayList<Task> copy = new ArrayList<>();

        for (Task task: tasks) {

            copy.add(new Task(task));

            if (task.getWorkToDo() == task.getWorkDone()) {
                tasks.remove(task);
            }
        }
        return new Report(this, copy);
    }

    @Override
    public void assignTask(Task task) {
        tasks.add(task);
    }

    double getAllWork() {

        double sumWork = 0;

        for (Task assignedTask: tasks) {
            sumWork += assignedTask.getWorkToDo();
        }

        return sumWork;
    }

    void work(double units) {

        if (units <= 0) {
            throw new IllegalArgumentException();
        }

        for (Task task: tasks) {
            if (units + task.getWorkDone() < task.getWorkToDo()) {
                task.setWorkDone(units + task.getWorkDone());
                return;
            } else {
                units = units + task.getWorkDone() % task.getWorkToDo();
                task.setWorkDone(task.getWorkToDo());
            }
        }
    }

    public String toString() {
        return super.toString()
                + "[tasks=" + tasks.toString()
                + "]";
    }
}
