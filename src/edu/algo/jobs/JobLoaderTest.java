package edu.algo.jobs;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobLoaderTest {

    @Test
    public void testLoad() throws IOException {
        List<Job> jobs = JobLoader.load("data/jobs.txt");
        assertEquals(10000, jobs.size());
        assertEquals(50, jobs.get(0).getLength());
        assertEquals(8, jobs.get(0).getWeight());

        assertEquals(15, jobs.get(jobs.size() - 1).getLength());
        assertEquals(68, jobs.get(jobs.size() - 1).getWeight());
    }
}
