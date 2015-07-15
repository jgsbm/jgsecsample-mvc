package jgs.bluemix.sample.exception;

/**
 * DataCache関連のExceptionをラップするRuntimeExceptionです.
 *
 * @author ryozo
 */
public class DataCacheRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5388638670876307092L;

    public DataCacheRuntimeException(Throwable cause) {
        super(cause);
    }
}
