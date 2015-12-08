import akka.actor.TypedActor;

/**
 * Created by curtis on 12/4/15.
 */
public class Tsa {
    public static void main(String[] args) {
        Jail jail = (Jail) TypedActor.newInstance(Jail.class, JailImpl.class);
        DocumentCheck documentCheck =
            (DocumentCheck) TypedActor.newInstance(DocumentCheck.class, DocumentCheck.class);
    }
}
