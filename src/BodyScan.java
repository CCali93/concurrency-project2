import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public class BodyScan extends UntypedActor {
    private ActorRef queue, security;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            Configure config = (Configure) message;
            ActorRef[] actors = config.getActors();

            queue = actors[0];
            security = actors[1];
        } else if(message instanceof Passenger) {
            //TODO: scan here
            queue.tell(new ScanReady());
        }
    }
}
