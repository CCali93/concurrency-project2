/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public class ScanResult {
    private final Passenger passenger;
    private final boolean success;

    /**
     * Message constructor
     *
     * @param passenger The passenger that was scanned
     * @param success Result of the scan the message was passed from
     */
    public ScanResult(Passenger passenger, boolean success) {
        this.passenger = passenger;
        this.success = success;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * @return true if passing, false if failing
     */
    public boolean isPassing() {
        return success;
    }
}
