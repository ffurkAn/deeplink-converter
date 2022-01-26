package com.atanriverdi.deeplinkconterter.controller;

import com.atanriverdi.deeplinkconterter.constant.Constants;
import com.atanriverdi.deeplinkconterter.constant.PathConstants;
import com.atanriverdi.deeplinkconterter.exception.DeeplinkException;
import com.atanriverdi.deeplinkconterter.exception.ErrorMessage;
import com.atanriverdi.deeplinkconterter.model.dto.DeeplinkRequestDTO;
import com.atanriverdi.deeplinkconterter.model.dto.ResponseDTO;
import com.atanriverdi.deeplinkconterter.model.dto.WebUrlRequestDTO;
import com.atanriverdi.deeplinkconterter.service.IDeeplinkConverterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Tag(name = Constants.SWAGGER_CONVERTER_API_TAG)
public class DeeplinkConverterController {

    private final IDeeplinkConverterService iDeeplinkConverterService;

    public DeeplinkConverterController(IDeeplinkConverterService iDeeplinkConverterService) {
        this.iDeeplinkConverterService = iDeeplinkConverterService;
    }

    @Operation(summary = "Produces a deeplink for corresponding web url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_200, description = "Success", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_400, description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_500, description = "Unexpected Error",
                    content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping(value = PathConstants.CONVERT_TO_DEEPLINK, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> convertToDeeplink(@Valid @RequestBody WebUrlRequestDTO webUrlRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iDeeplinkConverterService.convertToDeeplink(webUrlRequestDTO));
    }

    @Operation(summary = "Produces a web url for corresponding deeplink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_200, description = "Success", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_400, description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = Constants.HTTP_STATUS_CODE_500, description = "Unexpected Error", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping(value = PathConstants.CONVERT_TO_WEBURL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> convertToURL(@Valid @RequestBody DeeplinkRequestDTO deeplinkRequestDTO) throws Exception {
        throw new Exception("asdasdad");

//        return ResponseEntity.status(HttpStatus.OK).body(iDeeplinkConverterService.convertToURL(deeplinkRequestDTO));
    }
}
