package edu.algo.graphs.clustering;

import java.util.ArrayList;
import java.util.List;

public class Leader {

    private int id;
    private List<Integer> nodes = new ArrayList<>();

    public Leader(int node) {
        this.id = node;
        nodes.add(node);
    }

    public int getSize() {
        return nodes.size();
    }
    public void add(int index) {
        nodes.add(index);
    }
    public List<Integer> getNodes() {
        return nodes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Leader)
            return id == ((Leader)obj).id;
        return false;
    }
}
