import akka.actor.TypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by curtis on 12/7/15.
 */
public class SecurityImpl extends AbstractTsaActor implements Security{
    private Jail jail;

    private final Map<Passenger, ScanResult> scanResults = new HashMap<>();
    private int closesReceived = 0;

    @Override
    public void close() {
        // If message is a CloseMessage, check to see if we have received a CloseMessage from
        // both scanners. If so, send CloseMessage to Jail and shut down Security.
        closesReceived++;
        printMsg("Received " + closesReceived + " of " + Configuration.SCANNERS_PER_LINE
                + " close messages needed" );
        if (closesReceived >= Configuration.SCANNERS_PER_LINE) {
            jail.close(); // Tell jail this station has closed
            printMsg("Close sent to jail");
            super.close();
            printMsg("Closed");
        }

    }

    @Override
    public void receiveScanResult(ScanResult result) {
        boolean currentResult = result.isPassing();

        printMsg("Passenger " + result.getPassenger().getName() +
                " result received (" + (currentResult ? "pass" : "fail") + ")");

        // Add passenger and result to mapTypedActor  if passenger is not yet in map
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
                jail.receivePassenger(result.getPassenger());
            }
        }
    }

    @Override
    public void setJail(Jail jail) {
        this.jail = jail;
    }

    /**
     * Prints message from jail
     *
     * @param message - the message to print
     */
    private void printMsg(String message) {
        System.out.println("Jail: " + message);
    }
}
