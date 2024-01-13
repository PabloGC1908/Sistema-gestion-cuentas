package com.pgc.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentMethodRequest(
        @NotNull
        @NotBlank
        String paymentType,
        @NotNull
        @NotBlank
        String cardNumber,
        String ownerName,
        @NotNull
        @NotBlank
        String bank
) {
}
