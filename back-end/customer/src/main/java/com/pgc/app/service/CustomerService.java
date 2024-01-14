package com.pgc.app.service;

import com.pgc.app.dto.request.CustomerRequest;
import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Country;
import com.pgc.app.model.Customer;
import com.pgc.app.model.PaymentMethod;
import com.pgc.app.repository.CountryRepository;
import com.pgc.app.repository.CustomerRepository;
import com.pgc.app.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final CountryRepository countryRepository;

    public CustomerService(CustomerRepository customerRepository,
                           PaymentMethodRepository paymentMethodRepository,
                           CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.countryRepository = countryRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Transactional
    public String registerCustomer(CustomerRequest customerRequest) throws ResourceNotFoundException {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(customerRequest.paymentMethod());

        if (paymentMethodOptional.isEmpty())
            throw new ResourceNotFoundException("No se encontro el metodo de pago");

        String countryCode = getCountryCode(customerRequest.phone());
        Optional<Country> countryOptional = countryRepository.findById(countryCode);

        if (countryOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se encontro un pais con el codigo: %s", countryCode));

        Customer customer = Customer.builder()
                .customerCode(customerRequest.customerCode())
                .phone(customerRequest.phone())
                .registrationDate(customerRequest.registrationDate())
                .country(countryOptional.get())
                .paymentMethod(paymentMethodOptional.get())
                .active(false)
                .userId(customerRequest.userId())
                .build();

        customerRepository.save(customer);

        return "Cliente registrado correctamente";
    }

    @Transactional
    public String registerCustomers(List<CustomerRequest> customerRequestList) throws ResourceNotFoundException {
        for (CustomerRequest customerRequest : customerRequestList) {
            String response = registerCustomer(customerRequest);

            if (!response.equals("Cliente registrado correctamente"))
                break;
        }

        return "Clientes registrados correctamente";
    }

    public String getCountryCode(String phone) {
        if (phone.charAt(0) == '1' && phone.charAt(1) != '8'){
            return "1";
        }

        String countryCode = phone.substring(0, 2);

        return switch (countryCode) {
            case "50", "59", "35", "21" -> phone.substring(0, 3);
            case "18" -> phone.substring(0, 4);
            default -> countryCode;
        };
    }
}
