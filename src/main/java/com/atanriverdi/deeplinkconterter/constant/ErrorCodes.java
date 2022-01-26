package com.atanriverdi.deeplinkconterter.constant;

import com.atanriverdi.deeplinkconterter.exception.ErrorMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodes {

    public static final ErrorMessage ER001 = new ErrorMessage("ER001", "Unexpected error while logging the transaction.");
    public static final ErrorMessage ER002 = new ErrorMessage("ER002", "URL is malformed!");
    public static final ErrorMessage ER003 = new ErrorMessage("ER003", "Unsupported charset!");
}
