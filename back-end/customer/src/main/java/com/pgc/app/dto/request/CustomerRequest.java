package com.pgc.app.dto.request;

import com.pgc.app.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record CustomerRequest(
        @NotNull
        @NotBlank
        String customerCode,
        @Pattern(regexp = "\\d")
        String phone,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        @NotBlank
        Integer userId,
        @NotNull
        Date registrationDate
) {
}
