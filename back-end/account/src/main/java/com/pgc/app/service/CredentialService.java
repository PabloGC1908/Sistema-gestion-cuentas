package com.pgc.app.service;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Credential;
import com.pgc.app.repository.CredentialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CredentialService {
    private final CredentialRepository credentialRepository;

    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public List<Credential> getCredentials() {
        return credentialRepository.findAll();
    }

    public Credential getCredential(Long id) throws ResourceNotFoundException {
        Optional<Credential> credentialsOptional = credentialRepository.findById(id);

        if (credentialsOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se encontro la credencial con el id: %d", id));

        return credentialsOptional.get();
    }

    @Transactional
    public String registerCredential(Credential credential) {
        try {
            if (Objects.equals(credential.getEmail(), "NaN"))
                credential.setEmail(null);

            if (Objects.equals(credential.getPassword(), "NaN"))
                credential.setPassword(null);

            credentialRepository.save(credential);

            return "Las credenciales se registraron correctamente";
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public String registerCredentials(List<Credential> credentials) {
        for (Credential credential: credentials) {
            String response = registerCredential(credential);

            if (!response.equals("Las credenciales se registraron correctamente"))
                break;
        }

        return "Se registraron las credenciales correctamente";
    }

    @Transactional
    public String deleteCredential(Long id) {
        try {
            Optional<Credential> credential = credentialRepository.findById(id);

            if (credential.isEmpty())
                throw new ResourceNotFoundException(String.format("No se encontro la credencial con id: %d", id));

            credentialRepository.deleteById(id);

            return "Credencial eliminada correctamente";
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
