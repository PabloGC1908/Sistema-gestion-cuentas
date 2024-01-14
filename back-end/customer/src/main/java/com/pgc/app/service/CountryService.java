package com.pgc.app.service;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Country;
import com.pgc.app.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country getCountry(String code) throws ResourceNotFoundException {
        Optional<Country> country = countryRepository.findById(code);

        if (country.isEmpty())
            throw new ResourceNotFoundException(String.format("No se encontro al pais con codigo: %s", code));

        return country.get();
    }

    @Transactional
    public String registerCountry(Country country) {
        try {
            countryRepository.save(country);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "Pais registrado correctamente";
    }

    @Transactional
    public String deleteCountry(String code) {
        try {
            countryRepository.deleteById(code);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "Pais eliminado correctamente";
    }
}
