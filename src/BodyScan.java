/**
 * Created by curtis on 12/4/15.
 */

public interface BodyScan extends TsaActor {
    void setSecurity(Security security);
    void setQueue(Queue queue);
    void receivePassenger(Passenger passenger);
}