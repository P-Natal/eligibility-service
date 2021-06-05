package com.natal.eligibilityservice.infrastructure.repository;

import com.natal.eligibilityservice.infrastructure.entity.ClientEligibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEligibilityRepository extends JpaRepository<ClientEligibilityEntity, Long> {
    ClientEligibilityEntity findByClientDocument(String document);
}
