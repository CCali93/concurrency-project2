import akka.actor.TypedActor;

/**
 * Created by curtis on 12/7/15.
 */
public abstract class AbstractTsaActor extends TypedActor implements TsaActor {
    public int lineNumber;

    @Override
    public void close() {
        TypedActor.stop(this);
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
