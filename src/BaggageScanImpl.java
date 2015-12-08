import akka.actor.TypedActor;

/**
 * Created by curtis on 12/7/15.
 */
public class BaggageScanImpl extends TypedActor implements BaggageScan {
    private Security security;

    @Override
    public void receivePassenger(Passenger passenger) {
        //TODO: scan
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
