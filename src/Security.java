import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public class Security extends UntypedActor {
    private ActorRef jail;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Configure) {
            Configure config = (Configure) message;
            jail = config.getActors()[0];
        }
    }
}
