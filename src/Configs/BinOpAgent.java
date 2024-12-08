package Configs;

import java.util.function.BinaryOperator;

import Graph.TopicManagerSingleton.TopicManager;
import Graph.TopicManagerSingleton;
import Graph.Topic;
public class BinOpAgent{
    public final String agentName;
    public final TopicManager TopicManager;
    public final Topic input1;
    public final Topic input2;
    public final Topic output;
    public final BinaryOperator<Double> operation;
    public BinOpAgent(String name, String firstT, String secondT, String output,BinaryOperator<Double> op){
        this.agentName = name;
        this.TopicManager = TopicManagerSingleton.get();
        this.input1 = TopicManager.getTopic(firstT);
        this.input2 = TopicManager.getTopic(secondT);
        this.output = TopicManager.getTopic(output);
        this.operation = op;

    }

}
