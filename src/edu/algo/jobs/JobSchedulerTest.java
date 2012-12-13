package edu.algo.jobs;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class JobSchedulerTest {

    @Test
    public void testDiffSchedule() {
        List<Job> test = new ArrayList<>();
        test.add(new Job(4, 2)); // 2
        test.add(new Job(5, 3)); // 2
        test.add(new Job(6, 5)); // 1

        JobScheduler.calcOrderDiff(test);
        JobScheduler.schedule(test);

        Assert.assertEquals(2d, test.get(0).order);
        Assert.assertEquals(5, test.get(0).getWeight());

        Assert.assertEquals(2d, test.get(1).order);
        Assert.assertEquals(4, test.get(1).getWeight());

        Assert.assertEquals(1d, test.get(2).order);
        Assert.assertEquals(6, test.get(2).getWeight());
    }

    @Test
    public void testRatioSchedule() {
        List<Job> test = new ArrayList<>();
        test.add(new Job(4, 2)); //2
        test.add(new Job(5, 3)); //1.666
        test.add(new Job(6, 5)); //1.2

        JobScheduler.calcOrderRatio(test);
        JobScheduler.schedule(test);

        Assert.assertEquals(2d, test.get(0).order);
        Assert.assertEquals(4, test.get(0).getWeight());

        Assert.assertEquals(1.6666666666666667d, test.get(1).order);
        Assert.assertEquals(5, test.get(1).getWeight());

        Assert.assertEquals(1.2d, test.get(2).order);
        Assert.assertEquals(6, test.get(2).getWeight());
    }

    @Test
    public void testCalcCmptime() {
        List<Job> test = new ArrayList<>();
        test.add(new Job(4, 2)); // 4 * 2
        test.add(new Job(5, 3)); // 5 * 5
        test.add(new Job(6, 5)); // 6 * 10

        long total = JobScheduler.calcCompleationTime(test);

        Assert.assertEquals(93L, total);

        Assert.assertEquals(8L, test.get(0).cmptime);
        Assert.assertEquals(25L, test.get(1).cmptime);
        Assert.assertEquals(60L, test.get(2).cmptime);

    }
}
