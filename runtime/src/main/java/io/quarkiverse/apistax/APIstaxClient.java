package io.quarkiverse.apistax;

import io.quarkiverse.apistax.models.*;

public interface APIstaxClient {

    /**
     * Convert HTML to PDF
     *
     * @param payload HTML payload to convert (required)
     * @return byte[]
     * @throws ApiException if fails to make API call
     */
    byte[] convertHtmlToPdf(HtmlPayload payload) throws ApiException;

    /**
     * Convert HTML to PDF
     *
     * @param content The HTML document to be converted (required)
     * @return byte[]
     * @throws ApiException if fails to make API call
     */
    byte[] convertHtmlToPdf(String content) throws ApiException;

    /**
     * Generate a valid EPC QR Code
     *
     * @param payload QR Code payload to generate (required)
     * @return byte[]
     * @throws ApiException if fails to make API call
     */
    byte[] generateEpcQrCode(EpcQrCodePayload payload) throws ApiException;

    /**
     * Generate a valid EPC QR Code
     *
     * @param iban The international bank account number of the recipient (required)
     * @param recipient The recipients name (required)
     * @return byte[]
     * @throws ApiException if fails to make API call
     */
    byte[] generateEpcQrCode(String iban, String recipient) throws ApiException;

    /**
     * Verify a companies VAT ID if it is valid
     *
     * @param payload VAT ID payload to verify (required)
     * @return VatVerificationResult
     * @throws ApiException if fails to make API call
     */
    VatVerificationResult verifyVatId(VatVerificationPayload payload) throws ApiException;

    /**
     * Verify a companies VAT ID if it is valid
     *
     * @param vatId The VAT ID to check. (required)
     * @return VatVerificationResult
     * @throws ApiException if fails to make API call
     */
    VatVerificationResult verifyVatId(String vatId) throws ApiException;

    /**
     * Convert a known address to geo-coordinates
     *
     * @param payload Query payload to search for (required)
     * @return GeocodeResult
     * @throws ApiException if fails to make API call
     */
    GeocodeResult geocodeSearch(GeocodeSearchPayload payload) throws ApiException;

    /**
     * Convert a known address to geo-coordinates
     *
     * @param query A free-text address query. For example: \&quot;Heldenplatz, Wien\&quot; or \&quot;Wiedner Hauptstraße 32,
     *        1040 Wien\&quot; (required)
     * @return GeocodeResult
     * @throws ApiException if fails to make API call
     */
    GeocodeResult geocodeSearch(String query) throws ApiException;

    /**
     * Convert geo-coordinates to a postal address
     *
     * @param payload Coordinates payload to search for (required)
     * @return GeocodeResult
     * @throws ApiException if fails to make API call
     */
    GeocodeResult geocodeReverse(GeocodeReversePayload payload) throws ApiException;

    /**
     * Convert geo-coordinates to a postal address
     *
     * @param latitude The latitude coordinate of a point to search for. For example \&quot;48.20661\&quot; (required)
     * @param longitude The longitude coordinate of a point to search for. For example \&quot;16.36301\&quot; (required)
     * @return GeocodeResult
     * @throws ApiException if fails to make API call
     */
    GeocodeResult geocodeReverse(double latitude, double longitude) throws ApiException;
}
