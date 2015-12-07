import akka.actor.UntypedActor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by curtis on 12/4/15.
 */
public class Jail extends UntypedActor {

    private int closesReceived = 0;
    private final List<Passenger> prisoners = new ArrayList<>();

    @Override
    public void onReceive(final Object message) throws Exception {
        // If message is a Passenger, add them to the prisoner list.
        if (message instanceof Passenger) {
            printMsg("Passenger " + ((Passenger) message).getName() + " is jailed" );
            prisoners.add((Passenger) message);

        } else if (message instanceof CloseMessage) {
            // If message is a CloseMessage from a security station, increment our
            // count of close messages received and check to see if all have closed.
            closesReceived++;
            printMsg("Received " + closesReceived + " of " + Configure.NUM_LINES
                    + " close messages needed" );
            if (closesReceived >= Configure.NUM_LINES ) {
                // All security stations have shut down, shut down Jail.
                for (Passenger p : prisoners) {
                    printMsg("Passenger " + p.getName()
                            + " is moved to permanent detention.");
                }
                getContext().stop();
                printMsg("Closed");
            }
        }
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
