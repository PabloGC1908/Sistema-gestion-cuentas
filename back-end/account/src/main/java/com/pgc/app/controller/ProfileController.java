package com.pgc.app.controller;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Category;
import com.pgc.app.model.Profile;
import com.pgc.app.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profiles = profileService.getProfiles();

        if (profiles.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Byte id) {
        try {
            Profile profile = profileService.getProfile(id);

            return ResponseEntity.ok(profile);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<String> registerProfile(@RequestBody Profile profile) {
        String response = profileService.registerProfile(profile);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity<String> registerProfiles(@RequestBody List<Profile> profiles) {
        String response = profileService.registerProfiles(profiles);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Byte id) {
        String response = profileService.deleteProfile(id);

        return ResponseEntity.ok(response);
    }
}
