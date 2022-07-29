package io.quarkiverse.apistax;

import io.quarkiverse.apistax.apis.*;
import io.quarkiverse.apistax.http.ClientInterceptor;
import io.quarkiverse.apistax.models.*;

class APIstaxClientImpl implements APIstaxClient {

    private final HtmlToPdfApi htmlToPdfApi;
    private final EpcQrCodeApi epcQrCodeApi;
    private final VerifyVatIdApi verifyVatIdApi;
    private final GeocodingApi geocodingApi;
    private final ReverseGeocodingApi reverseGeocodingApi;

    public APIstaxClientImpl(String apiKey) {
        var client = new ApiClient();

        client.setRequestInterceptor(new ClientInterceptor(apiKey));

        htmlToPdfApi = new HtmlToPdfApi(client);
        epcQrCodeApi = new EpcQrCodeApi(client);
        verifyVatIdApi = new VerifyVatIdApi(client);
        geocodingApi = new GeocodingApi(client);
        reverseGeocodingApi = new ReverseGeocodingApi(client);
    }

    @Override
    public byte[] convertHtmlToPdf(HtmlPayload payload) throws ApiException {
        return htmlToPdfApi.convertHtmlToPdf(payload);
    }

    @Override
    public byte[] convertHtmlToPdf(String content) throws ApiException {
        return convertHtmlToPdf(new HtmlPayload().content(content));
    }

    @Override
    public byte[] generateEpcQrCode(EpcQrCodePayload payload) throws ApiException {
        return epcQrCodeApi.generateEpcQrCodeJson(payload);
    }

    @Override
    public byte[] generateEpcQrCode(String iban, String recipient) throws ApiException {
        return generateEpcQrCode(new EpcQrCodePayload().iban(iban).recipient(recipient));
    }

    @Override
    public VatVerificationResult verifyVatId(VatVerificationPayload payload) throws ApiException {
        return verifyVatIdApi.verifyVatIdJson(payload);
    }

    @Override
    public VatVerificationResult verifyVatId(String vatId) throws ApiException {
        return verifyVatId(new VatVerificationPayload().vatId(vatId));
    }

    @Override
    public GeocodeResult geocodeSearch(GeocodeSearchPayload payload) throws ApiException {
        return geocodingApi.geocodeSearchJson(payload);
    }

    @Override
    public GeocodeResult geocodeSearch(String query) throws ApiException {
        return geocodeSearch(new GeocodeSearchPayload().query(query));
    }

    @Override
    public GeocodeResult geocodeReverse(GeocodeReversePayload payload) throws ApiException {
        return reverseGeocodingApi.geocodeReverseJson(payload);
    }

    @Override
    public GeocodeResult geocodeReverse(double latitude, double longitude) throws ApiException {
        return geocodeReverse(new GeocodeReversePayload().latitude(latitude).longitude(longitude));
    }
}
