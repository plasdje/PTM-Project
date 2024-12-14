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
        for (Node n : this) {
            if (n.hasCycles()) {
                System.out.println("Cycle detected starting at node: " + n.getName());
                return true;
            }
        }
        return false;
    }

//    public void printGraph() {
//        System.out.println("Graph structure:");
//        for (Node node : this) {
//            System.out.println("Node: " + node.getName());
//            for (Node edge : node.getNode()) {
//                System.out.println("  -> " + edge.getName());
//            }
//        }
//        System.out.println();
//    }
//    public void printTopics() {
//        TopicManager tm = TopicManagerSingleton.get();
//        Collection<Topic> topics = tm.getTopics();
//
//        for (Topic t : topics) {
//            System.out.println("Topic: " + t.name);
//            System.out.println("  Subscribers:");
//            for (Agent a : t.subs) {
//                System.out.println("    " + a.getName());
//            }
//            System.out.println("  Publishers:");
//            for (Agent a : t.pubs) {
//                System.out.println("    " + a.getName());
//            }
//        }
//    }

    public void createFromTopics(){
        for (Node node : this) {
            node.getNode().clear();
        }
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