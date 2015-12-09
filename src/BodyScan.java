/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface BodyScan extends TsaActor {
    void setSecurity(Security security);
    void setQueue(Queue queue);
    void receivePassenger(Passenger passenger);

    void setLineNumber(int lineNumber);

    void close();
}