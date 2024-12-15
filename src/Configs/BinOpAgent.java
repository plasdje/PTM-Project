package Configs;

import java.util.function.BinaryOperator;

import Graph.TopicManagerSingleton.TopicManager;
import Graph.TopicManagerSingleton;
import Graph.Topic;
import Graph.Agent;
import Graph.Message;
public class BinOpAgent implements Agent{
    private final String agentName;
    private final TopicManager TopicManager;
    private final Topic input1;
    private final Topic input2;
    private final Topic output;
    private final BinaryOperator<Double> operation;
    private double val1;
    private double val2;
    public BinOpAgent(String name, String firstT, String secondT, String output,BinaryOperator<Double> op){
        this.agentName = name;
        this.TopicManager = TopicManagerSingleton.get();
        this.input1 = TopicManager.getTopic(firstT);
        this.input2 = TopicManager.getTopic(secondT);
        this.output = TopicManager.getTopic(output);
        this.operation = op;
        this.input1.subscribe(this);
        this.input2.subscribe(this);
        this.output.addPublisher(this);
        reset();
    }

    @Override
    public String getName() {
        return this.agentName;
    }

    @Override
    public void reset() {
        this.val1 = 0.0;
        this.val2 = 0.0;
        input1.publish(new Message(0.0));
        input2.publish(new Message(0.0));
    }

    @Override
    public void callback(String topic, Message msg) {
        if(Double.isNaN(msg.asDouble))
            return;
        if(topic.equals(this.input1.name)){
            val1 = msg.asDouble;
            val2 = (input2.getMsg() != null) ? input2.getMsg().asDouble : 0;
        }
        else if(topic.equals(this.input2.name)) {
            val2 = msg.asDouble;
            val1 = (input1.getMsg() != null) ? input1.getMsg().asDouble : 0;
        }

        double result = this.operation.apply(val1, val2);
        this.output.publish(new Message(result));
    }

    @Override
    public void close() {}
}



