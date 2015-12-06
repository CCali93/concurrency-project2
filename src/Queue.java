import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.LinkedList;

/**
 * Created by curtis on 12/4/15.
 */
public class Queue extends UntypedActor {
    private ActorRef bagScan, bodyScan;
    private LinkedList<Passenger> waitingInLine;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            ActorRef[] connections = ((Configure) message).getActors();

            waitingInLine = new LinkedList<>();
            bagScan = connections[0];
            bodyScan = connections[1];
        }
    }
}
