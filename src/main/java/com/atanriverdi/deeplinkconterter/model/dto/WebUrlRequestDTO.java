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
public class WebUrlRequestDTO {

    @Pattern(regexp = RegexpConstants.REGEX_WEB_URL)
    private String body;

    @Override
    public String toString() {
        return "WebUrlRequestDTO{" +
                "body='" + body + '\'' +
                '}';
    }

}
