package edu.algo.jobs;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class JobLoaderTest {

    @Test
    public void testLoad() throws IOException {
        List<Job> jobs = JobLoader.load("data/jobs.txt");
        Assert.assertEquals(10000, jobs.size());
        Assert.assertEquals(50, jobs.get(0).getLength());
        Assert.assertEquals(8, jobs.get(0).getWeight());

        Assert.assertEquals(15, jobs.get(jobs.size() - 1).getLength());
        Assert.assertEquals(68, jobs.get(jobs.size() - 1).getWeight());
    }
}
