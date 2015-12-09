/**
 * Created by curtis on 12/4/15.
 */
public interface BaggageScan extends TsaActor {
    void receivePassenger(Passenger passenger);
    void setSecurity(Security security);

    void setLineNumber(int lineNumber);

    void close();
}
