/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface BaggageScan extends TsaActor {
    void receivePassenger(Passenger passenger);
    void setSecurity(Security security);

    void setLineNumber(int lineNumber);

    void close();
}
