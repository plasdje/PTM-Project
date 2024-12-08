package Graph;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
public class TopicManagerSingleton {

    public static class TopicManager{
        private final static TopicManager instance = new TopicManager();

        public final Map<String, Topic> topics;
        private TopicManager(){
            this.topics = new HashMap<>();
        }
        public Topic getTopic(String name){
            return topics.computeIfAbsent(name, Topic::new);
        }
        public Collection<Topic> getTopics() {
            return new ArrayList<>(topics.values());
        }
        public void clear(){
            this.topics.clear();
        }
    }
    public static TopicManager get(){
        return TopicManager.instance;
    }


}
