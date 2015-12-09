/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface DocumentCheck extends TsaActor {
    void addCheckLine(Queue queue);
    void sendPassengerToNextAvailableLine(Passenger passenger);

    void close();
}
