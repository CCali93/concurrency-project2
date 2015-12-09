import akka.actor.TypedActor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public class JailImpl extends TypedActor implements Jail {
    private final List<Passenger> prisoners = new ArrayList<>();
    private int closesReceived = 0;

    @Override
    public void close() {
        // If message is a CloseMessage from a security station, increment our
        // count of close messages received and check to see if all have closed.
        closesReceived++;
        printMsg("Received " + closesReceived + " of " + Configuration.NUM_LINES
                + " close messages needed" );
        if (closesReceived >= Configuration.NUM_LINES ) {
            // All security stations have shut down, shut down Jail.
            for (Passenger p : prisoners) {
                printMsg("Passenger " + p.getName()
                        + " is moved to permanent detention");
            }
            TypedActor.stop(this);
            printMsg("Closed");
            System.exit(0);
        }
    }

    @Override
    public int getLineNumber() {
        return 0;
    }

    @Override
    public void setLineNumber(int lineNumber) {

    }

    @Override
    public void receivePassenger(Passenger passenger) {
        // If message is a Passenger, add them to the prisoner list.
        printMsg("Passenger " + passenger.getName() + " is jailed" );
        prisoners.add(passenger);
    }

    /**
     * Prints message from jail
     *
     * @param message - the message to print
     */
    private void printMsg(String message) {
        System.out.println("        Jail: " + message);
    }
}
