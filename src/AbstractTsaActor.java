import akka.actor.TypedActor;

/**
 * Created by curtis on 12/7/15.
 */
public abstract class AbstractTsaActor extends TypedActor implements TsaActor {
    @Override
    public void close() {
        TypedActor.stop(this);
    }
}
