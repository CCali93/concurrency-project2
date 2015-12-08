import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public interface BaggageScan {
    void receivePassenger(Passenger passenger);
    void setSecurity(Security security);
}
