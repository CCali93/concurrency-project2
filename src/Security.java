import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by curtis on 12/4/15.
 */
public interface Security {
    void close();
    void receiveScanResult(ScanResult result);
    void setJail(Jail jail);
    void setLineNumber(int lineNumber);
}
