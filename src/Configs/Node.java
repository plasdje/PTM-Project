package Configs;

import java.sql.Array;
import java.util.*;
import Graph.Message;


public class Node {
    private String name;
    private List<Node> edges;
    private Message msg;
    Node(String name){
        this.name = name;
        this.edges = new ArrayList<>();
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public List<Node> getNode(){return this.edges;}
    public void setNode(List<Node> l){this.edges = l;}
    public Message getMsg(){return this.msg;}
    public void setMsg(Message msg){this.msg = msg;}
    public void addEdge(Node n){
        if(!edges.contains(n)){
            edges.add(n);
        }
    }
    public void removeEdge(Node n){
        if(edges.contains(n)){
            edges.remove(n);
        }
    }
    public boolean hasCycles(){
        Set<Node> visited = new HashSet<>();
        Set<Node> stack = new HashSet<>();
        for(Node n : edges){
            if(!visited.contains(n)){
                if(recursiveNode(n, visited, stack)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean recursiveNode(Node current, Set<Node> visited, Set<Node> stack){
        stack.add(current);
        visited.add(current);

        for(Node s : current.getNode()){
            if(!visited.contains(s))
            {
                if(recursiveNode(s,visited,stack))
                    return true;
            }
            else if(stack.contains(s))
                return true;
        }
        stack.remove(current);
        return false;
    }

}