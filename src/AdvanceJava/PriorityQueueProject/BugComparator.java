package AdvanceJava.PriorityQueueProject;

import java.util.Comparator;

public class BugComparator implements Comparator<Bug> {
    @Override
    public int compare(Bug bug, Bug t1) {
        if (bug.getPriority() > t1.getPriority()) {
            return -1;
        }
        if (bug.getPriority() < t1.getPriority()) {
            return +1;
        }
        return 0;
    }
}
