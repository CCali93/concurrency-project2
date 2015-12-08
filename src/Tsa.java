import akka.actor.TypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public class Tsa {
    public static void main(String[] args) {
        Jail jail = TypedActor.newInstance(Jail.class, JailImpl.class);
        DocumentCheck documentCheck =
            TypedActor.newInstance(DocumentCheck.class, DocumentCheck.class);

        //Configure all the connections for each line
        for(int i = 0; i < Configuration.NUM_LINES; i++) {
            Security security = TypedActor.newInstance(Security.class, SecurityImpl.class);
            security.setJail(jail);
            security.setLineNumber(i);

            BaggageScan bagScan = TypedActor.newInstance(BaggageScan.class, BaggageScanImpl.class);
            bagScan.setLineNumber(i);
            bagScan.setSecurity(security);

            BodyScan bodyScan = TypedActor.newInstance(BodyScan.class, BodyScanImpl.class);;
            bodyScan.setLineNumber(i);
            bodyScan.setSecurity(security);

            Queue queue = TypedActor.newInstance(Queue.class, QueueImpl.class);
            queue.setLineNumber(i);
            queue.setBagScan(bagScan);
            queue.setBodyScan(bodyScan);

            bodyScan.setQueue(queue);
            documentCheck.addCheckLine(queue);
        }

        //Send all the passengers in
        for(int i = 0; i < Configuration.NUM_PASSENGERS; i++) {
            documentCheck.sendPassengerToNextAvailableLine(
                new Passenger(Configuration.PASSENGER_NAMES[i])
            );
        }
    }
}
