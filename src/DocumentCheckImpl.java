import java.util.ArrayList;

/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public class DocumentCheckImpl extends AbstractTsaActor implements DocumentCheck {
    private int currentLine = 0;
    private ArrayList<Queue> checkLines = new ArrayList<>();

    @Override
    public void addCheckLine(Queue queue) {
        checkLines.add(queue);
    }

    @Override
    public void sendPassengerToNextAvailableLine(Passenger passenger) {
        printMsg("Passenger " + passenger.getName() + " arrives");

        // If message is a Passenger, perform the document check.
        if ((Math.random() * 100) < Configuration.FAIL_PROBABILITY) {
            // Document check failed, send away
            printMsg("Passenger " + passenger.getName() + " fails document check and is turned away");
        } else {
            // Document check passes. Send to next line in rotation
            printMsg("Passenger " + passenger.getName() + " passes document check and is sent to line " +
                    +this.currentLine);
            checkLines.get(currentLine).addPassenger(passenger);
            currentLine = ((currentLine + 1) % checkLines.size());
        }
    }

    @Override
    public void close() {
        printMsg("All passengers processed. Closing down for the day");
        for(Queue checkLine : checkLines) {
            printMsg("Close sent to Queue " + checkLine.getLineNumber());
            checkLine.close();
        }

        printMsg("Closed");
        super.close();
    }

    private void printMsg(String msg) {
        System.out.println("Document Check: " + msg);
    }
}
