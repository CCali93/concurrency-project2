import akka.actor.ActorRef;
import akka.actor.TypedActor;

import java.util.LinkedList;

/**
 * Created by curtis on 12/7/15.
 */
public class QueueImpl extends AbstractTsaActor implements Queue {
    private BaggageScan bagScan;
    private BodyScan bodyScan;
    private LinkedList<Passenger> waitingInLine = new LinkedList<>();

    private QueueImpl() {
        super();
    }

    @Override
    public void addPassenger(Passenger passenger) {
        waitingInLine.add(passenger);
        bagScan.receivePassenger(passenger);
    }

    @Override
    public void sendPassengerTodyBoScanner() {
        Passenger passenger = waitingInLine.poll();

        if(passenger != null) {
            //TODO: send
            bodyScan.receivePassenger(passenger);
        }
    }

    @Override
    public void setBagScan(BaggageScan bagScan) {
        this.bagScan = bagScan;
    }

    @Override
    public void setBodyScan(BodyScan bodyScan) {
        this.bodyScan = bodyScan;
    }

    @Override
    public void close() {
        bagScan.close();
        bodyScan.close();

        super.close();
    }
}
