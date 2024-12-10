package com.gorai.employee.service;

import com.gorai.employee.model.EmploymentAgreement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmploymentAgreementService {
    EmploymentAgreement createAgreement(EmploymentAgreement agreement);
    Optional<EmploymentAgreement> getAgreementById(Long id);
    EmploymentAgreement updateAgreement(Long id, EmploymentAgreement agreement);
    EmploymentAgreement updatePartialAgreement(Long id, EmploymentAgreement partialUpdate);
    void deleteAgreement(Long id);
    List<EmploymentAgreement> getAllAgreements();
    List<EmploymentAgreement> findAgreementsByRole(String role);
    List<EmploymentAgreement> listEmploymentAgreements(String employeeName, String role, String startDate, String endDate);
    List<EmploymentAgreement> searchEmploymentAgreements(Map<String, String> filters);
}
