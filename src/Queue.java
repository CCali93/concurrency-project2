/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface Queue extends TsaActor {
    void addPassenger(Passenger passenger);

    void sendPassengerToBodyScanner();

    void setBagScan(BaggageScan bagScan);

    void setBodyScan(BodyScan bodyScan);

    void bodyScanReady(boolean ready);

    void close();

    int getLineNumber();

    void setLineNumber(int lineNumber);
}
