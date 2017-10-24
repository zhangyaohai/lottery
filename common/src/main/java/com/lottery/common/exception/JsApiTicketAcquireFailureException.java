package com.lottery.common.exception;


public class JsApiTicketAcquireFailureException extends RuntimeException {

    private static final long serialVersionUID = 8209595579855388792L;

    public JsApiTicketAcquireFailureException() {
    }

    public JsApiTicketAcquireFailureException(String message) {
        super(message);
    }

    public JsApiTicketAcquireFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsApiTicketAcquireFailureException(Throwable cause) {
        super(cause);
    }

    public JsApiTicketAcquireFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
