package io.quarkiverse.apistax;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Function;

import org.openapitools.jackson.nullable.JsonNullableModule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.quarkiverse.apistax.models.*;

class APIstaxClientImpl implements APIstaxClient {

    private static final String baseUri = "https://api.apistax.io";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String apiKey;

    public APIstaxClientImpl(String apiKey) {
        this.apiKey = apiKey;

        httpClient = HttpClient.newHttpClient();

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new JsonNullableModule());
    }

    @Override
    public byte[] convertHtmlToPdf(HtmlPayload payload) throws APIstaxException {
        return requestBinary("/v1/html-to-pdf", payload);
    }

    @Override
    public byte[] convertHtmlToPdf(String content) throws APIstaxException {
        return convertHtmlToPdf(new HtmlPayload().content(content));
    }

    @Override
    public byte[] generateEpcQrCode(EpcQrCodePayload payload) throws APIstaxException {
        return requestBinary("/v1/epc-qr-code", payload);
    }

    @Override
    public byte[] generateEpcQrCode(String iban, String recipient) throws APIstaxException {
        return generateEpcQrCode(new EpcQrCodePayload().iban(iban).recipient(recipient));
    }

    @Override
    public VatVerificationResult verifyVatId(VatVerificationPayload payload) throws APIstaxException {
        return requestJson("/v1/vat-verification", payload, VatVerificationResult.class);
    }

    @Override
    public VatVerificationResult verifyVatId(String vatId) throws APIstaxException {
        return verifyVatId(new VatVerificationPayload().vatId(vatId));
    }

    @Override
    public GeocodeResult geocodeSearch(GeocodeSearchPayload payload) throws APIstaxException {
        return requestJson("/v1/geocode/search", payload, GeocodeResult.class);
    }

    @Override
    public GeocodeResult geocodeSearch(String query) throws APIstaxException {
        return geocodeSearch(new GeocodeSearchPayload().query(query));
    }

    @Override
    public GeocodeResult geocodeReverse(GeocodeReversePayload payload) throws APIstaxException {
        return requestJson("/v1/geocode/reverse", payload, GeocodeResult.class);
    }

    @Override
    public GeocodeResult geocodeReverse(double latitude, double longitude) throws APIstaxException {
        return geocodeReverse(new GeocodeReversePayload().latitude(latitude).longitude(longitude));
    }

    private byte[] requestBinary(String path, Object body) {
        return request(path, body, inputStream -> {
            try {
                return inputStream.readAllBytes();
            } catch (IOException e) {
                throw new APIstaxException(e);
            }
        });
    }

    private <T> T requestJson(String path, Object body, Class<T> type) {
        return request(path, body, inputStream -> {
            try {
                return objectMapper.readValue(inputStream, type);
            } catch (IOException e) {
                throw new APIstaxException(e);
            }
        });
    }

    private <T> T request(String path, Object body, Function<InputStream, T> mapper) {
        try {
            var request = createRequestBuilder(path, body).build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() < 200 && response.statusCode() > 299) {
                try {
                    var errorMessage = objectMapper.readValue(response.body(), ErrorMessage.class);
                    throw new APIstaxException(errorMessage.getMessages());
                } catch (IOException e) {
                    throw new APIstaxException(List.of("message.unknownError"));
                }
            }

            return mapper.apply(response.body());
        } catch (IOException e) {
            throw new APIstaxException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new APIstaxException(e);
        }
    }

    private HttpRequest.Builder createRequestBuilder(String path, Object body) {
        try {
            var bodyData = objectMapper.writeValueAsBytes(body);

            var requestBuilder = HttpRequest.newBuilder();
            requestBuilder.uri(URI.create(baseUri + path));
            requestBuilder.header("Content-Type", "application/json");
            requestBuilder.header("Authorization", "Bearer " + apiKey);
            requestBuilder.header("User-Agent", "quarkus-apistax");
            requestBuilder.method("POST", HttpRequest.BodyPublishers.ofByteArray(bodyData));

            return requestBuilder;
        } catch (IOException e) {
            throw new APIstaxException(e);
        }
    }
}
