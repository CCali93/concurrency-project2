import akka.actor.ActorRef;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by curtis on 12/6/15.
 */
public class Configure {
    private ActorRef[] connectedActors;

    public Configure(ActorRef[] connectedActors) {
        this.connectedActors = Arrays.copyOf(connectedActors, connectedActors.length);
    }

    public ActorRef[] getActors() {
        return Arrays.copyOf(this.connectedActors, this.connectedActors.length);
    }
}
