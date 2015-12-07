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

    public static final int NUM_PASSENGERS = 3;

    public static final String[] PASSENGER_NAMES = {"Bob, Joe, Lisa"};

    public static final int NUM_LINES = 2;

    public static final int FAIL_PROBABILITY = 20;

    public static final int SCANNERS_PER_LINE = 2;

}
