package edu.algo.jobs;

public class Job {
    public int weight;
    public int length;
    public double order;
    public long cmptime;

    public Job(int w, int l) {
        this.weight = w;
        this.length = l;
    }

    public int getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }
    
    @Override
    public String toString() {
        return String.format("(%d,%d,%f,%d)", weight, length, order, cmptime);
    }
}
