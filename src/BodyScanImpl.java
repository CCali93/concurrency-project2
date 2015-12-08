import akka.actor.TypedActor;

/**
 * Created by curtis on 12/7/15.
 */
public class BodyScanImpl extends AbstractTsaActor implements BodyScan {
    private Queue queue;
    private Security security;

    @Override
    public void close() {
        security.close();
        super.close();
    }

    @Override
    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void receivePassenger(Passenger passenger) {
        //TODO: scan
        queue.sendPassengerTodyBoScanner(); //Once done send a new passenger
    }
}
