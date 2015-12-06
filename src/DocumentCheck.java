import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;

/**
 * Created by curtis on 12/4/15.
 */
public class DocumentCheck extends UntypedActor {
    private ArrayList<ActorRef> checkLines;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            Configure config = (Configure) message;
            checkLines = new ArrayList<>();

            for(ActorRef queue : config.getActors()) {
                checkLines.add(queue);
            }
        }
    }
}
