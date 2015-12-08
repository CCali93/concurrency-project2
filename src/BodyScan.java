import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by curtis on 12/4/15.
 */

public interface BodyScan {
    void setSecurity(Security security);
    void setQueue(Queue queue);
    void receivePassenger(Passenger passenger);
}