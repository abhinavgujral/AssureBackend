package com.backend.Assure.repository;

import com.backend.Assure.entity.PolicyBookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyBookingsRepository extends JpaRepository<PolicyBookings, Long> {
}
