package com.pgc.app.service;

import com.pgc.app.dto.request.PaymentMethodRequest;
import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.PaymentMethod;
import com.pgc.app.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod getPaymentMethod(Integer id) throws ResourceNotFoundException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);

        if (paymentMethodOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se pudo encontrar el metodo de pago con el id: %d", id));

        return paymentMethodOptional.get();
    }

    @Transactional
    public String registerPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        paymentMethodRepository.save(PaymentMethod.builder()
                .paymentType(paymentMethodRequest.paymentType())
                .cardNumber(paymentMethodRequest.cardNumber())
                .ownerName(paymentMethodRequest.ownerName())
                .bank(paymentMethodRequest.bank())
                .build());

        return "Metodo de pago registrado con exito";
    }

    @Transactional
    public String updatePaymentMethod(Integer id, PaymentMethodRequest paymentMethodRequest) throws ResourceNotFoundException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);

        if (paymentMethodOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se pudo encontrar el metodo con el id: %d", id));

        paymentMethodRepository.save(PaymentMethod.builder()
                .id(id)
                .paymentType(paymentMethodRequest.paymentType())
                .cardNumber(paymentMethodRequest.cardNumber())
                .ownerName(paymentMethodRequest.ownerName())
                .bank(paymentMethodRequest.bank())
                .build());

        return "Se actualizo correctamente el metodo de pago";
    }

    @Transactional
    public String deletePaymentMethod(Integer id) throws ResourceNotFoundException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(id);

        if (paymentMethodOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se pudo encontrar el metodo de pago con el id: %d", id));

        paymentMethodRepository.deleteById(id);

        return "Metodo de pago eliminado correctamente";
    }
}
