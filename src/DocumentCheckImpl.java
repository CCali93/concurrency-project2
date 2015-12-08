import akka.actor.TypedActor;

import java.util.ArrayList;

/**
 * Created by curtis on 12/7/15.
 */
public class DocumentCheckImpl extends AbstractTsaActor implements DocumentCheck {
    private int currentLine = 0;
    private ArrayList<Queue> checkLines = new ArrayList<>();

    @Override
    public void addCheckLine(Queue queue) {
        checkLines.add(queue);
    }

    @Override
    public void sendPassengerToNextAvailableLine(Passenger passenger) {
        checkLines.get(currentLine).addPassenger(passenger);

        currentLine = (currentLine + 1) % checkLines.size();
    }

    @Override
    public void close() {
        for(Queue checkLine : checkLines) {
            checkLine.close();
        }

        super.close();
    }
}
