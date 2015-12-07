import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;

/**
 * Created by curtis on 12/4/15.
 */
public class DocumentCheck extends UntypedActor {
    int currentLine;
    private ArrayList<ActorRef> checkLines;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            Configure config = (Configure) message;
            checkLines = new ArrayList<>();

            for(ActorRef queue : config.getActors()) {
                checkLines.add(queue);
            }

            currentLine = 0;
        } else if(message instanceof Passenger) {
            checkLines.get(currentLine).tell(message);

            currentLine = (currentLine + 1) % checkLines.size();
        }
    }
}
