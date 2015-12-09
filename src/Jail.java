/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface Jail extends TsaActor {
    void receivePassenger(Passenger passenger);

    void close();
}
