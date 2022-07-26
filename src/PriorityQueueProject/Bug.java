package PriorityQueueProject;

public class Bug {
    private String name;
    private int priority;

    Bug(String name,int priority){
        this.name=name;
        this.priority=priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return ((super.hashCode()/100)*100)+57;
    }

    @Override
    public String toString() {
        return getName();
    }
}
