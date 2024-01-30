package com.pgc.app.controller;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Credential;
import com.pgc.app.service.CredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credentials")
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    public ResponseEntity<List<Credential>> getCredentials() {
        List<Credential> credentials = credentialService.getCredentials();

        if (credentials.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credential> getCredential(@PathVariable Long id) {
        try {
            Credential credential = credentialService.getCredential(id);

            return ResponseEntity.ok(credential);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<String> registerCredential(@RequestBody Credential credential) {
        String response = credentialService.registerCredential(credential);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity<String> registerCredentials(@RequestBody List<Credential> credentials) {
        String response = credentialService.registerCredentials(credentials);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCredential(@PathVariable Long id) {
        String response = credentialService.deleteCredential(id);

        return ResponseEntity.ok(response);
    }
}
