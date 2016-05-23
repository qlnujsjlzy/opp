package edu.exam.online.professional.utils;

/**
 * 异常继承运行性异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3806456902100358000L;
    /**
     * @Fields code : 错误代码
     */
    private int code;

    public int getCode() {
        return code;
    }


    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
