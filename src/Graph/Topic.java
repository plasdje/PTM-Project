package Graph;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    public final List<Agent> subs;
    public final List<Agent> pubs;
    Topic(String name){
        this.name=name;
        this.subs = new ArrayList<Agent>();
        this.pubs = new ArrayList<Agent>();
    }
    public String getName(){
        return this.name;
    }
    public void subscribe(Agent a){
        if(!subs.contains(a))
            subs.add(a);
    }
    public void unsubscribe(Agent a){
        if(subs.contains(a))
             subs.remove(a);
    }

    public void publish(Message m){
        for(Agent agent : this.subs){
            agent.callback(this.name,m);
        }
    }

    public void addPublisher(Agent a){
        if(!pubs.contains(a))
            pubs.add(a);
    }

    public void removePublisher(Agent a){
        if(pubs.contains(a))
            pubs.remove(a);
    }
    public List<Agent> getSubs() {
        return subs;
    }
    public List<Agent> getPubs() {
        return pubs;
    }


}



















