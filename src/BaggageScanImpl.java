/**
 * Created by curtis on 12/7/15.
 */
public class BaggageScanImpl extends AbstractTsaActor implements BaggageScan {
    private Security security;


    @Override
    public void receivePassenger(Passenger passenger) {
        printMsg("Passenger " + passenger.getName() + "'s baggage enters");
        // If message is a Passenger, perform the baggage check.
        if ((Math.random() * 100) < Configuration.FAIL_PROBABILITY) {
            // Baggage check failed, send a fail message to Security
            printMsg("Passenger " + passenger.getName() + "'s baggage fails");
            security.receiveScanResult(new ScanResult(passenger, false));
        } else {
            // Baggage check success, send a pass message to Security
            printMsg("Passenger " + passenger.getName() + "'s baggage passes");
            security.receiveScanResult(new ScanResult(passenger, true));
        }
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public void close() {
        printMsg("Close received");
        security.close();
        printMsg("Close sent to security");
        super.close();
        printMsg("Closed");
    }

    private void printMsg(String message) {
        System.out.println("BaggageScan: " + message);
    }
}
