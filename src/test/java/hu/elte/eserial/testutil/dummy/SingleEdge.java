package hu.elte.eserial.testutil.dummy;

public class SingleEdge {

    private Integer edgeId;
    private String edgeName;
    private SingleNode node1;
    private SingleNode node2;

    public SingleEdge(Integer edgeId, String edgeName, SingleNode node1, SingleNode node2) {
        this.edgeId = edgeId;
        this.edgeName = edgeName;
        this.node1 = node1;
        this.node2 = node2;
    }

    public String getEdgeName() {
        return edgeName;
    }

    public void setEdgeName(String edgeName) {
        this.edgeName = edgeName;
    }

    public Integer getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Integer edgeId) {
        this.edgeId = edgeId;
    }

    public SingleNode getNode1() {
        return node1;
    }

    public void setNode1(SingleNode node1) {
        this.node1 = node1;
    }

    public SingleNode getNode2() {
        return node2;
    }

    public void setNode2(SingleNode node2) {
        this.node2 = node2;
    }
}
