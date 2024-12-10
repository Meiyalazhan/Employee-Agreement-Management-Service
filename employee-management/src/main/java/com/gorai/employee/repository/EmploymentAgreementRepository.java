package com.gorai.employee.repository;

import com.gorai.employee.model.EmploymentAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentAgreementRepository extends JpaRepository<EmploymentAgreement, Long> {
    List<EmploymentAgreement> findByRole(String role);
}
