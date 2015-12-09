/**
 * Created by curtis on 12/7/15.
 */
public class BodyScanImpl extends AbstractTsaActor implements BodyScan {
    private Queue queue;
    private Security security;

    @Override
    public void close() {
        printMsg("Close received");
        security.close();
        printMsg("Close sent to security");
        super.close();
        printMsg("Closed");
    }

    @Override
    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void receivePassenger(Passenger passenger) {
        printMsg("Passenger " + passenger.getName() + " enters body scanner");
        // If msg is a Passenger, perform the body scan.
        if ((Math.random() * 100) < Configuration.FAIL_PROBABILITY) {
            // Body scan fails, send a fail message to Security
            printMsg("Passenger " + passenger.getName() + " fails body scan");
            security.receiveScanResult(new ScanResult(passenger, false));
        } else {
            // Body scan passes, send a pass message to Security
            printMsg("Passenger " + passenger.getName() + " passes body scan");
            security.receiveScanResult(new ScanResult(passenger, true));
        }
        // Tell scan queue to send the next passenger
        queue.bodyScanReady(true);
        queue.sendPassengerToBodyScanner();
    }


    private void printMsg(String msg) {
        System.out.println("Body Scan " + getLineNumber() + ": " + msg);
    }
}
