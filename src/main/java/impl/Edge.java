package impl;

public class Edge {
    Node target;
    float bandwidth;

    public Edge(Node target, float bandwidth) {
        this.target = target;
        this.bandwidth = bandwidth;
    }

    public float cost() {
        return 1 / bandwidth;
    }
}
