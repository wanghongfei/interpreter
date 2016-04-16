package cn.fh.interpreter.exception;

/**
 * Created by whf on 4/16/16.
 */
public class SyntaxError extends RuntimeException {
    public SyntaxError(String msg) {
        super(msg);
    }
}
