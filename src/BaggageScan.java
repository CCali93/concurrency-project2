import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public class BaggageScan extends UntypedActor {
    private ActorRef security;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            Configure config = (Configure) message;
            security = config.getActors()[0];
        }
    }
}
