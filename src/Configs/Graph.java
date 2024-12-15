package Configs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Graph.TopicManagerSingleton.TopicManager;
import Graph.Agent;
import Graph.Topic;
import Graph.TopicManagerSingleton;

public class Graph extends ArrayList<Node>{

    private Map<String, Node> nodeMap = new HashMap<>();

    public Graph(){
        super();
    }
    public boolean hasCycles() {
        for (Node node : this) {
            if (node.hasCycles()) {
                return true;
            }
        }
        return false;
    }
    public void clearGraph() {
        for (Node node : this) {
            node.getEdges().clear();
        }
        this.clear();
        nodeMap.clear();
    }
    public void createFromTopics(){
        clearGraph();
        TopicManager tm = TopicManagerSingleton.get();
        Collection<Topic> topics = tm.getTopics();

        for (Topic t : topics) {
            Node topicNode = ensureNodeExists("T" + t.name);

            for (Agent a : t.subs) {
                Node agentNode = ensureNodeExists("A" + a.getName());
                topicNode.addEdge(agentNode);
            }

            for (Agent a : t.pubs) {
                Node agentNode = ensureNodeExists("A" + a.getName());
                agentNode.addEdge(topicNode);
            }
        }
    }

    private Node ensureNodeExists(String name) {
        Node n = nodeMap.get(name);
        if (n == null) {
            n = new Node(name);
            this.add(n);
            nodeMap.put(name, n);
        }
        return n;
    }

}