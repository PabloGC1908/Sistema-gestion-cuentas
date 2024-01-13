package com.pgc.app.controller;

import com.pgc.app.dto.request.PaymentMethodRequest;
import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.PaymentMethod;
import com.pgc.app.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();

        if (paymentMethods.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(paymentMethods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethod(id);

            if (paymentMethod == null)
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(paymentMethod);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> registerPaymentMethod(@RequestBody PaymentMethodRequest paymentMethod) {
        try {
            String response = paymentMethodService.registerPaymentMethod(paymentMethod);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePaymentMethod(@PathVariable Integer id,
                                                      @RequestBody PaymentMethodRequest paymentMethodRequest) throws ResourceNotFoundException {
        try {
            String response = paymentMethodService.updatePaymentMethod(id, paymentMethodRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            String response = paymentMethodService.deletePaymentMethod(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
