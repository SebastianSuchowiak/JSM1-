package Task1;

public class Task implements Cloneable{

    private final String taskName;
    private final double workToDo;
    private double workDone = 0;

    public Task(String taskName, double workToDo) {

        if (workToDo < 0) {
            throw new IllegalArgumentException();
        }
        this.workToDo = workToDo;
        this.taskName = taskName;
    }

    public Task(Task other) {
        this.taskName = other.taskName;
        this.workToDo = other.workToDo;
        this.workDone = other.workDone;
    }

    String getTaskName() {
        return taskName;
    }

    double getWorkToDo() {
        return workToDo;
    }

    double getWorkDone() {
        return workDone;
    }

    void setWorkDone(double workDone) {
        this.workDone = workDone;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[taskName=" + taskName
                + ", workToDo=" + workToDo
                + ", workDone=" + workDone
                + "]";
    }
}
