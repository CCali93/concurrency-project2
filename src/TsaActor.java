/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface TsaActor {
    void close();

    int getLineNumber();

    void setLineNumber(int lineNumber);
}
