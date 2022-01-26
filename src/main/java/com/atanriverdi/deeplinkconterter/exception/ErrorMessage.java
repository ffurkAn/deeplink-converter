package com.atanriverdi.deeplinkconterter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private String errCode;
    private String errDesc;
}
