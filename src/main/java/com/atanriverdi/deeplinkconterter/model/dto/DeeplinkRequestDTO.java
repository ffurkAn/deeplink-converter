package com.atanriverdi.deeplinkconterter.model.dto;

import com.atanriverdi.deeplinkconterter.constant.RegexpConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeeplinkRequestDTO {

    @Pattern(regexp = RegexpConstants.REGEX_DEEPLINK)
    private String body;

    @Override
    public String toString() {
        return "DeeplinkRequestDTO{" +
                "body='" + body + '\'' +
                '}';
    }
}
