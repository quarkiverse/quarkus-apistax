package io.quarkiverse.apistax;

import io.apistax.client.APIstaxClient;
import io.apistax.client.APIstaxException;
import io.apistax.models.*;

import java.nio.charset.StandardCharsets;

public class APIstaxClientMock implements APIstaxClient {
    @Override
    public byte[] convertHtmlToPdf(HtmlPayload htmlPayload) throws APIstaxException {
        return "PDF".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] convertHtmlToPdf(String s) throws APIstaxException {
        return "PDF".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] generateEpcQrCode(EpcQrCodePayload epcQrCodePayload) throws APIstaxException {
        return "EPC_QR_CODE".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] generateEpcQrCode(String s, String s1) throws APIstaxException {
        return "EPC_QR_CODE".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public VatVerificationResult verifyVatId(VatVerificationPayload vatVerificationPayload) throws APIstaxException {
        return new VatVerificationResult()
                .valid(true)
                .name("ACME Company")
                .address("Street 1 12345 City")
                .countryCode("AT");
    }

    @Override
    public VatVerificationResult verifyVatId(String s) throws APIstaxException {
        return new VatVerificationResult()
                .valid(true)
                .name("ACME Company")
                .address("Street 1 12345 City")
                .countryCode("AT");
    }

    @Override
    public GeocodeResult geocodeSearch(GeocodeSearchPayload geocodeSearchPayload) throws APIstaxException {
        return new GeocodeResult()
                .address(
                        new GeocodeResultAddress()
                                .city("City")
                                .country("Austria")
                                .countryCode("AT")
                                .street("Street")
                                .houseNumber("1")
                )
                .position(
                        new GeocodeResultPosition()
                                .latitude(48.208101)
                                .longitude(16.373760)
                );
    }

    @Override
    public GeocodeResult geocodeSearch(String s) throws APIstaxException {
        return new GeocodeResult()
                .address(
                        new GeocodeResultAddress()
                                .city("City")
                                .country("Austria")
                                .countryCode("AT")
                                .street("Street")
                                .houseNumber("1")
                )
                .position(
                        new GeocodeResultPosition()
                                .latitude(48.208101)
                                .longitude(16.373760)
                );
    }

    @Override
    public GeocodeResult geocodeReverse(GeocodeReversePayload payload) throws APIstaxException {
        return new GeocodeResult()
                .address(
                        new GeocodeResultAddress()
                                .city("City")
                                .country("Austria")
                                .countryCode("AT")
                                .street("Street")
                                .houseNumber("1")
                )
                .position(
                        new GeocodeResultPosition()
                                .latitude(payload.getLatitude())
                                .longitude(payload.getLongitude())
                );
    }

    @Override
    public GeocodeResult geocodeReverse(double v, double v1) throws APIstaxException {
        return new GeocodeResult()
                .address(
                        new GeocodeResultAddress()
                                .city("City")
                                .country("Austria")
                                .countryCode("AT")
                                .street("Street")
                                .houseNumber("1")
                )
                .position(
                        new GeocodeResultPosition()
                                .latitude(v)
                                .longitude(v1)
                );
    }

    @Override
    public IndexResult fetchIndex(Index index, IndexFrequency indexFrequency) throws APIstaxException {
        var result = new IndexResult()
                .id(index.getValue())
                .frequency(indexFrequency);

        if(indexFrequency == IndexFrequency.YEARLY) {
            for(int i = 0; i < 10; i++) {
                result = result.addValuesItem(indexValue(2000 + i, 100f + i));
            }
        } else {
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 12; j++) {
                    result = result.addValuesItem(indexValue(2000 + i, 1 + j, 100f + i + (j  / 10f)));
                }
            }
        }

        return result;
    }

    private IndexValue indexValue(int year, float value) {
        return new IndexValue()
                .year(year)
                .value(value);
    }

    private IndexValue indexValue(int year, int month, float value) {
        return new IndexValue()
                .year(year)
                .month(month)
                .value(value);
    }

    @Override
    public byte[] generateSwissQrInvoice(SwissQrInvoicePayload swissQrInvoicePayload) throws APIstaxException {
        return "SWISS_QR_INVOICE".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] generateSwissQrInvoice(SwissQrInvoicePayload swissQrInvoicePayload, SwissQrInvoiceFormat swissQrInvoiceFormat) throws APIstaxException {
        return "SWISS_QR_INVOICE".getBytes(StandardCharsets.UTF_8);
    }
}
