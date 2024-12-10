package com.gorai.employee.service;

import com.gorai.employee.model.EmploymentAgreement;
import com.gorai.employee.repository.EmploymentAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmploymentAgreementServiceImpl implements EmploymentAgreementService {

    @Autowired
    private EmploymentAgreementRepository repository;

    @Override
    public EmploymentAgreement createAgreement(EmploymentAgreement agreement) {
        return repository.save(agreement);
    }

    @Override
    public Optional<EmploymentAgreement> getAgreementById(Long id) {
        return repository.findById(id);
    }

    @Override
    public EmploymentAgreement updateAgreement(Long id, EmploymentAgreement updatedAgreement) {
        Optional<EmploymentAgreement> existingAgreement = repository.findById(id);

        if (existingAgreement.isEmpty()) {
            throw new RuntimeException("Employment Agreement not found with ID: " + id);
        }

        EmploymentAgreement agreement = existingAgreement.get();
        agreement.setEmployeeName(updatedAgreement.getEmployeeName());
        agreement.setRole(updatedAgreement.getRole());
        agreement.setStartDate(updatedAgreement.getStartDate());
        agreement.setEndDate(updatedAgreement.getEndDate());
        agreement.setSalary(updatedAgreement.getSalary());
        agreement.setTerms(updatedAgreement.getTerms());
        agreement.setOtherDetails(updatedAgreement.getOtherDetails());

        return repository.save(agreement);
    }

    @Override
    public EmploymentAgreement updatePartialAgreement(Long id, EmploymentAgreement partialUpdate) {
        Optional<EmploymentAgreement> optionalAgreement = repository.findById(id);

        if (optionalAgreement.isEmpty()) {
            throw new RuntimeException("Employment Agreement not found with ID: " + id);
        }

        EmploymentAgreement agreement = optionalAgreement.get();

        if (partialUpdate.getEmployeeName() != null) {
            agreement.setEmployeeName(partialUpdate.getEmployeeName());
        }
        if (partialUpdate.getRole() != null) {
            agreement.setRole(partialUpdate.getRole());
        }
        if (partialUpdate.getStartDate() != null) {
            agreement.setStartDate(partialUpdate.getStartDate());
        }
        if (partialUpdate.getEndDate() != null) {
            agreement.setEndDate(partialUpdate.getEndDate());
        }
        if (partialUpdate.getSalary() != null) {
            agreement.setSalary(partialUpdate.getSalary());
        }
        if (partialUpdate.getTerms() != null) {
            agreement.setTerms(partialUpdate.getTerms());
        }
        if (partialUpdate.getOtherDetails() != null) {
            agreement.setOtherDetails(partialUpdate.getOtherDetails());
        }

        return repository.save(agreement);
    }

    @Override
    public void deleteAgreement(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Employment Agreement not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<EmploymentAgreement> getAllAgreements() {
        return repository.findAll();
    }

    @Override
    public List<EmploymentAgreement> findAgreementsByRole(String role) {
        return repository.findByRole(role);
    }
    @Override
    public List<EmploymentAgreement> listEmploymentAgreements(String employeeName, String role, String startDate, String endDate) {
        List<EmploymentAgreement> agreements = repository.findAll();

        // Apply optional filters
        if (employeeName != null && !employeeName.isEmpty()) {
            agreements = agreements.stream()
                    .filter(a -> a.getEmployeeName().equalsIgnoreCase(employeeName))
                    .collect(Collectors.toList());
        }
        if (role != null && !role.isEmpty()) {
            agreements = agreements.stream()
                    .filter(a -> a.getRole().equalsIgnoreCase(role))
                    .collect(Collectors.toList());
        }
        if (startDate != null && !startDate.isEmpty()) {
            LocalDate start = LocalDate.parse(startDate);
            agreements = agreements.stream()
                    .filter(a -> !a.getStartDate().isBefore(start))
                    .collect(Collectors.toList());
        }
        if (endDate != null && !endDate.isEmpty()) {
            LocalDate end = LocalDate.parse(endDate);
            agreements = agreements.stream()
                    .filter(a -> !a.getEndDate().isAfter(end))
                    .collect(Collectors.toList());
        }

        return agreements;
    }

    @Override
    public List<EmploymentAgreement> searchEmploymentAgreements(Map<String, String> filters) {
        List<EmploymentAgreement> agreements = repository.findAll();

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue();

            switch (key) {
                case "employeeName":
                    agreements = agreements.stream()
                            .filter(a -> a.getEmployeeName().equalsIgnoreCase(value))
                            .collect(Collectors.toList());
                    break;
                case "role":
                    agreements = agreements.stream()
                            .filter(a -> a.getRole().equalsIgnoreCase(value))
                            .collect(Collectors.toList());
                    break;
                case "startDate":
                    LocalDate startDate = LocalDate.parse(value);
                    agreements = agreements.stream()
                            .filter(a -> !a.getStartDate().isBefore(startDate))
                            .collect(Collectors.toList());
                    break;
                case "endDate":
                    LocalDate endDate = LocalDate.parse(value);
                    agreements = agreements.stream()
                            .filter(a -> !a.getEndDate().isAfter(endDate))
                            .collect(Collectors.toList());
                    break;
                case "salaryMin":
                    double salaryMin = Double.parseDouble(value);
                    agreements = agreements.stream()
                            .filter(a -> a.getSalary() >= salaryMin)
                            .collect(Collectors.toList());
                    break;
                case "salaryMax":
                    double salaryMax = Double.parseDouble(value);
                    agreements = agreements.stream()
                            .filter(a -> a.getSalary() <= salaryMax)
                            .collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }

        return agreements;
    }
}
