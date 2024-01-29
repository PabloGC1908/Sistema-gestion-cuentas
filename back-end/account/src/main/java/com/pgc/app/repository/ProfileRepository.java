package com.pgc.app.repository;

import com.pgc.app.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Byte> {
}
