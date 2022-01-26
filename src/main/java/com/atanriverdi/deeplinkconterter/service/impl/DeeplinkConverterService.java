package com.atanriverdi.deeplinkconterter.service.impl;

import com.atanriverdi.deeplinkconterter.config.ApplicationConfiguration;
import com.atanriverdi.deeplinkconterter.constant.Constants;
import com.atanriverdi.deeplinkconterter.constant.ErrorCodes;
import com.atanriverdi.deeplinkconterter.exception.DeeplinkException;
import com.atanriverdi.deeplinkconterter.model.dto.DeeplinkRequestDTO;
import com.atanriverdi.deeplinkconterter.model.dto.ResponseDTO;
import com.atanriverdi.deeplinkconterter.model.dto.WebUrlRequestDTO;
import com.atanriverdi.deeplinkconterter.service.IDeeplinkConverterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Slf4j
@CacheConfig(cacheNames = "converterCache")
@Service
public class DeeplinkConverterService implements IDeeplinkConverterService {

    public static final Map<String, String> URL_TO_DEEPLINK_MAP =
            Map.of(
                    "boutiqueId", "CampaignId",
                    "merchantId", "MerchantId");
    public static final Map<String, String> DEEPLINK_TO_URL_MAP =
            Map.of(
                    "CampaignId", "boutiqueId",
                    "MerchantId", "merchantId");
    private ApplicationConfiguration applicationConfiguration;

    public DeeplinkConverterService(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    /**
     * @param webUrlRequestDTO
     * @return
     */
    @Cacheable(cacheNames = "webUrlCache")
    @Override
    public ResponseDTO convertToDeeplink(WebUrlRequestDTO webUrlRequestDTO) {

        try {
            URI uri = new URI(webUrlRequestDTO.getBody());

            String path = uri.getPath();

            if (path.contains(Constants.productDetailPrefix)) {
                return new ResponseDTO(getProductDetailDeeplink(path, uri));
            } else if (Constants.searchPrefix.equals(path)) {
                return new ResponseDTO(getSearchDeeplink(uri.getRawQuery()));
            } else {
                return new ResponseDTO(Constants.emptyDeepLink);
            }
        } catch (URISyntaxException e) {
            log.error("MalformedUrlException", e);
            throw new DeeplinkException(ErrorCodes.ER002);
        }

    }


    private String getSearchDeeplink(String query) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.deepLinkQueryBase);
        sb.append(query);
        return sb.toString();
    }


    private String getProductDetailDeeplink(String path, URI uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.deepLinkProductBase);
        sb.append(path.split(Constants.productDetailPrefix)[1]);

        URIBuilder uriBuilder = new URIBuilder(uri);
        List<NameValuePair> urlParameters = uriBuilder.getQueryParams();

        for (NameValuePair pair : urlParameters) {
            if (!applicationConfiguration.getUrlParamWhiteList().contains(pair.getName())) {
                log.error("Request parameter [{}] is not permitted, so that it will be ignored!");
                continue;
            } else {
                sb.append("&");
                sb.append(URL_TO_DEEPLINK_MAP.get(pair.getName()));
                sb.append("=");
                sb.append(pair.getValue());
            }
        }

        return sb.toString();
    }

    /**
     * @param deeplinkRequestDTO
     * @return
     */
    @Cacheable(cacheNames = "deeplinkCache")
    @Override // todo improve distinction between product and search page
    public ResponseDTO convertToURL(DeeplinkRequestDTO deeplinkRequestDTO) throws Exception {

        try {

            URI uri = new URI(deeplinkRequestDTO.getBody());

            URIBuilder uriBuilder = new URIBuilder(deeplinkRequestDTO.getBody());
            List<NameValuePair> urlParameters = uriBuilder.getQueryParams();

            NameValuePair pageDetailPair = urlParameters.remove(0);

            if (pageDetailPair.getValue().equals("Product")) {
                return new ResponseDTO(getProductDetailURL(urlParameters));
            } else if (pageDetailPair.getValue().equals("Search")) {
                return new ResponseDTO(getSearchURL(uri.getRawQuery().split("&")[1]));
            } else {
                return new ResponseDTO(Constants.trendyolBaseUrl);
            }
        } catch (URISyntaxException e) {
            log.error("URISyntaxException", e);
            throw new DeeplinkException(ErrorCodes.ER002);
        }
    }

    private String getSearchURL(String queryParam) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.qBase);
        sb.append(queryParam.split("=")[1]);

        return sb.toString();
    }

    private String getProductDetailURL(List<NameValuePair> urlParameters) throws URISyntaxException {

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.urlProductBase);
        sb.append(urlParameters.remove(0).getValue());

        for (NameValuePair pair : urlParameters) {
            if (!applicationConfiguration.getDeeplinkParamWhiteList().contains(pair.getName())) {
                log.error("Request parameter [{}] is not permitted, so that it will be ignored!");
                continue;
            } else {
                sb.append("&");
                sb.append(DEEPLINK_TO_URL_MAP.get(pair.getName()));
                sb.append("=");
                sb.append(pair.getValue());
            }
        }

        return sb.toString();
    }

    private String getPageTypeFromQuery(String body) throws URISyntaxException {
        URI uri = new URI(body);
        return uri.getQuery().split("&")[0].split("=")[1];

    }
}
