package com.pgc.app.service;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Profile;
import com.pgc.app.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfile(Byte id) throws ResourceNotFoundException {
        Optional<Profile> profileOptional = profileRepository.findById(id);

        if (profileOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("No se encontro el perfil con el id: %d", id));

        return profileOptional.get();
    }

    @Transactional
    public String registerProfile(Profile profile) {
        try {
            if (Objects.equals(profile.getProfile(), "NaN"))
                profile.setProfile(null);

            profileRepository.save(profile);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

        return "Perfil registrado correctamente";
    }

    @Transactional
    public String registerProfiles(List<Profile> profiles) {
        for (Profile profile : profiles) {
            String response = registerProfile(profile);

            if (!response.equals("Perfil registrado correctamente"))
                break;
        }

        return "Perfiles registrado correctamente";
    }

    @Transactional
    public String deleteProfile(Byte id) {
        try {
            profileRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

        return "Perfil eliminado correctamente";
    }
}
