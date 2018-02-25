package hu.elte.eserial.testutil.dummy;

public class SingleNode {

    private Integer nodeId;
    private String nodeName;
    private SingleEdge edge;

    public SingleNode(Integer nodeId, String nodeName) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public SingleEdge getEdge() {
        return edge;
    }

    public void setEdge(SingleEdge edge) {
        this.edge = edge;
    }
}
