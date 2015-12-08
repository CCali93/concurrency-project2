import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.LinkedList;

/**
 * Created by curtis on 12/4/15.
 */
public interface Queue extends TsaActor {
    void addPassenger(Passenger passenger);
    void sendPassengerTodyBoScanner();
    void setBagScan(BaggageScan bagScan);
    void setBodyScan(BodyScan bodyScan);
}
