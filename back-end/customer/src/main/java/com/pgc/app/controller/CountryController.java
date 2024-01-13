package com.pgc.app.controller;

import com.pgc.app.model.Country;
import com.pgc.app.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> countries = countryService.getCountries();

        if (!countries.isEmpty())
            return ResponseEntity.ok(countries);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        Country country = countryService.getCountry(code);

        if (country == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<String> postCountry(@RequestBody Country country) {
        String message = countryService.registerCountry(country);

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteCountry(@PathVariable String code) {
        String response = countryService.deleteCountry(code);

        return ResponseEntity.ok(response);
    }
}
