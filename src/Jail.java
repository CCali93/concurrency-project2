import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by curtis on 12/4/15.
 */
public interface Jail {
    void close();
    void receivePassenger(Passenger passenger);
}
