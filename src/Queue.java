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
        } else if(message instanceof Passenger) {
            bagScan.tell(message);
            waitingInLine.add((Passenger) message);
        } else if (message instanceof ScanReady) {
            Passenger passenger = waitingInLine.poll();

            if(passenger != null) {
                bodyScan.tell(passenger);
            }
        }
    }
}
