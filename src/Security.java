import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by curtis on 12/4/15.
 */
public class Security extends UntypedActor {
    private final int lineNumber;
    private ActorRef jail;
    
    private final Map<Passenger, ScanResult> scanResults = new HashMap<>();
    private int closesReceived;

    /**
     * Constructor for the SecurityStation
     *
     * @param lineNumber the line number sending passengers to this security station
     * @param jail the jail to which to send detainees
     */
    public Security(int lineNumber, ActorRef jail) {
        this.lineNumber = lineNumber;
        this.jail = jail;
    }

    @Override
    public void onReceive(final Object message) throws Exception {
        if(message instanceof Configure) {
            // If message is a Configure message, set up actor configurations
            Configure config = (Configure) message;
            jail = config.getActors()[0];
        }
        if (message instanceof ScanResult) {
            // If message is a ScanResult, check to see if we have received a result for
            // this passenger already. If so, decide if passenger passes or fails. 
            // Else, store the passenger and result in map and wait for other scan result.
            ScanResult result = (ScanResult) message;
            boolean currentResult = result.isPassing();

            printMsg("Passenger " + result.getPassenger().getName() +
                    " result received (" + (currentResult ? "pass" : "fail") + ")");

            // Add passenger and result to map if passenger is not yet in map
            if (!scanResults.containsKey(result.getPassenger())) {
                scanResults.put(result.getPassenger(), result);
            } else { // Else, entry for passenger exists in map
                ScanResult previousScanResult = scanResults.remove(result.getPassenger()); // Get previous entry
                boolean previousResult = previousScanResult.isPassing();
                if (previousResult && currentResult) { // If passenger has passed both scanners
                    // Passenger leaves system
                    printMsg("Passenger " + result.getPassenger().getName() +
                            " continues to airport");
                } else {
                    // Passenger has failed one or more scans, send to Jail.
                    if (currentResult || previousResult){
                        printMsg("Passenger " + result.getPassenger().getName() +
                                " failed one scan and is sent to jail");
                    } else {
                        printMsg("Passenger " + result.getPassenger().getName() +
                                " failed both scans and is sent to jail");
                    }
                    jail.tell(result.getPassenger());
                }
            }
        } else if (message instanceof CloseMessage) {
            // If message is a CloseMessage, check to see if we have received a CloseMessage from
            // both scanners. If so, send CloseMessage to Jail and shut down Security.
            closesReceived++;
            printMsg("Received " + closesReceived + " of " + Configure.SCANNERS_PER_LINE
                    + " close messages needed" );
            if (closesReceived >= Configure.SCANNERS_PER_LINE) {
                jail.tell(message); // Tell jail this station has closed
                printMsg("Close sent to jail");
                getContext().stop(); // Close this station down
                printMsg("Closed");
            }
        }
    }

    /**
     * Prints message from Security
     *
     * @param message the message to print
     */
    private void printMsg(String message) {
        System.out.println("Security " + lineNumber + ": " + message);
    }

}
