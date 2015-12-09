/**
 * @author Austin Cowan, Curtis Cali, Kurt Poquette
 */
public interface Security extends TsaActor {
    void receiveScanResult(ScanResult result);
    void setJail(Jail jail);
    void setLineNumber(int lineNumber);

    void close();
}
