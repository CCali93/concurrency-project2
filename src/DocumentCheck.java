import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;

/**
 * Created by curtis on 12/4/15.
 */
public interface DocumentCheck extends TsaActor {
    void addCheckLine(Queue queue);
    void sendPassengerToNextAvailableLine(Passenger passenger);
}
