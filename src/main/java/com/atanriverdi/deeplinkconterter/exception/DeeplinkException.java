package com.atanriverdi.deeplinkconterter.exception;


import lombok.Getter;

@Getter
public class DeeplinkException extends RuntimeException {

    private String code;

    public DeeplinkException(String message, String code) {
        super(message);
        this.code = code;
    }

    public DeeplinkException(ErrorMessage errorMessage) {
        super(errorMessage.getErrDesc());
        this.code = errorMessage.getErrCode();
    }

    @Override
    public String toString() {
        return "DeeplinkException{" +
                "code='" + code + '\'' +
                "message='" + getMessage() + '\'' +
                '}';
    }
}
