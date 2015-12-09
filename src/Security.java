/**
 * Created by curtis on 12/4/15.
 */
public interface Security extends TsaActor {
    void receiveScanResult(ScanResult result);
    void setJail(Jail jail);
    void setLineNumber(int lineNumber);
}
