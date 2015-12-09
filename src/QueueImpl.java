import java.util.LinkedList;

/**
 * Created by curtis on 12/7/15.
 */
public class QueueImpl extends AbstractTsaActor implements Queue {
    private BaggageScan bagScan;
    private BodyScan bodyScan;
    private LinkedList<Passenger> waitingInLine = new LinkedList<>();
    private boolean bodyScannerReady = true;
    private boolean closeReceived = false;

    public QueueImpl() {
        super();
    }

    @Override
    public void addPassenger(Passenger passenger) {
        printMsg("Passenger " + passenger.getName() + " enters the queue");
        waitingInLine.add(passenger);
        printMsg("Passenger " + passenger.getName() + " places baggage on scanner");
        bagScan.receivePassenger(passenger);
        if (bodyScannerReady) { // Go to body scan if it is ready
            sendPassengerToBodyScanner();
            bodyScanReady(false);
        }
    }

    @Override
    public void sendPassengerToBodyScanner() {
        if (bodyScannerReady) {
            Passenger passenger = waitingInLine.pollFirst();
            // There are passengers waiting
            if (passenger != null) {
                printMsg("Passenger " + passenger.getName() + " is signaled to enter the body scanner");
                bodyScan.receivePassenger(passenger);
                bodyScannerReady = false;
            }
        }

        if (closeReceived) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                close();
            }
        }
    }

    @Override
    public void setBagScan(BaggageScan bagScan) {
        this.bagScan = bagScan;
    }

    @Override
    public void setBodyScan(BodyScan bodyScan) {
        this.bodyScan = bodyScan;
    }

    @Override
    public void close() {
        this.closeReceived = true;
        // Proceed if queue is empty and nobody is in the body scanner
        if (!waitingInLine.isEmpty() || !bodyScannerReady) {
            sendPassengerToBodyScanner();
        }

        // Queue is empty and body scanner is empty
        printMsg("Close received");
        printMsg("Close sent to baggage scanner");
        bagScan.close();
        printMsg("Close sent to body scanner");
        bodyScan.close();
        printMsg("Closed");
        super.close();
    }

    private void printMsg(String msg) {
        System.out.println("Queue " + getLineNumber() + ": " + msg);
    }

    public void bodyScanReady(boolean ready) {
        this.bodyScannerReady = ready;
    }
}
