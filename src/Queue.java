/**
 * Created by curtis on 12/4/15.
 */
public interface Queue extends TsaActor {
    void addPassenger(Passenger passenger);

    void sendPassengerToBodyScanner();
    void setBagScan(BaggageScan bagScan);
    void setBodyScan(BodyScan bodyScan);

    void bodyScanReady(boolean ready);
}
