package com.atanriverdi.deeplinkconterter.service;

import com.atanriverdi.deeplinkconterter.model.dto.DeeplinkRequestDTO;
import com.atanriverdi.deeplinkconterter.model.dto.ResponseDTO;
import com.atanriverdi.deeplinkconterter.model.dto.WebUrlRequestDTO;

public interface IDeeplinkConverterService {
    ResponseDTO convertToURL(DeeplinkRequestDTO deeplinkRequestDTO) throws Exception;

    ResponseDTO convertToDeeplink(WebUrlRequestDTO webUrlRequestDTO);
}
