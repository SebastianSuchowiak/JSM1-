package Task1;

import java.util.ArrayList;

class Report {

    private ArrayList<ArrayList<Task>> tasks = new ArrayList<>();
    private ArrayList<Employee> contractors = new ArrayList<>();

    Report(Employee employee, ArrayList<Task> newTasks) {
        contractors.add(employee);
        tasks.add(newTasks);
    }

    Report() {

    }

    String submit() {

        StringBuilder readyReport = new StringBuilder();
        Employee currentContractor;
        ArrayList<Task> currentTasks;
        Task currentTask;

        for (int i = 0; i < contractors.size(); ++i) {

            currentContractor = contractors.get(i);
            currentTasks = tasks.get(i);

            readyReport.append("Employee " + currentContractor.getRole() + "-" + currentContractor.getName() + ":\n");

            if (currentTasks.size() == 0) {
                readyReport.append("No tasks assigned\n");
            } else {
                for (int j = 0; j < currentTasks.size(); ++j) {
                    currentTask = currentTasks.get(j);

                    readyReport.append("Task" + j + " " + currentTask.getTaskName() + " " +
                            currentTask.getWorkDone() + " out of " + currentTask.getWorkToDo() + "\n");
                }
            }
        }

        return readyReport.toString();

    }

    Report combine(Report other) {

        Report combined = new Report();

        combined.tasks.addAll(other.tasks);
        combined.tasks.addAll(this.tasks);
        combined.contractors.addAll(other.contractors);
        combined.contractors.addAll(this.contractors);

        return combined;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "tasks=" + tasks
                + ", contractors=" + contractors
                + "]";
    }
}
