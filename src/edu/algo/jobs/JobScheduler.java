package edu.algo.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.algo.Utils;

public class JobScheduler {

    public static void main(String[] args) throws IOException {
        List<Job> jobs = JobLoader.load("data/jobs.txt");

        List<Job> cp1 = new ArrayList<>(jobs);
        List<Job> cp2 = new ArrayList<>(jobs);

        calcOrderDiff(cp1);
        schedule(cp1);
        Utils.print("Total compleation time for diff ordering = " + calcCompleationTime(cp1));

        calcOrderRatio(cp2);
        schedule(cp2);
        Utils.print("Total compleation time for ratio ordering = " + calcCompleationTime(cp2));
        //Utils.print(Arrays.toString(jobs.toArray()));
    }

    public static void schedule(List<Job> jobs) {
        Collections.sort(jobs, new Comparator<Job>() {
            /**
             * Sort in decreasing order of the difference (weight - length).
             * For equal prefer higher weight.
             * @param j1
             * @param j2
             * @return
             */
            @Override
            public int compare(Job j1, Job j2) {
                int cmp = (j1.order > j2.order) ? -1 : ((j1.order == j2.order) ? 0 : 1);
                if (cmp == 0) {
                    cmp = (j1.weight > j2.weight) ? -1 : ((j1.weight == j2.weight) ? 0 : 1);
                    //Utils.print("there was one " + j1 + ", " + j2);
                }
                return cmp;
            }
        });
    }

    public static void calcOrderDiff(List<Job> jobs) {
        for (Job job : jobs) {
            job.order = job.getWeight() - job.getLength();
        }
    }

    public static void calcOrderRatio(List<Job> jobs) {
        for (Job job : jobs) {
            job.order = ((double)job.getWeight()) / job.getLength();
        }
    }

    public static long calcCompleationTime(List<Job> jobs) {
        int i = 0;
        long cmptime = 0L;
        long cmptimeTotal = 0L;
        for (Job job : jobs) {
            cmptime += job.length;
            job.cmptime = job.weight * cmptime;
            cmptimeTotal += job.cmptime;
//            if (i%10 == 0) {
//                Utils.print("iteration " + i + " - " + cmptimeTotal);
//            }
            i++;
        }
        return cmptimeTotal;
    }
}
