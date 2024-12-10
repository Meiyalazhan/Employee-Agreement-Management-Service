package com.gorai.employee.controller;

import com.gorai.employee.model.EmploymentAgreement;
import com.gorai.employee.service.EmploymentAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employment-agreements")
public class EmploymentAgreementController {

    @Autowired
    private EmploymentAgreementService service;

    @PostMapping
    public ResponseEntity<EmploymentAgreement> createAgreement(@RequestBody EmploymentAgreement agreement) {
        return ResponseEntity.ok(service.createAgreement(agreement));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploymentAgreement> getAgreementById(@PathVariable Long id) {
        return service.getAgreementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmploymentAgreement> updateAgreement(
            @PathVariable Long id, @RequestBody EmploymentAgreement updatedAgreement) {
        return ResponseEntity.ok(service.updateAgreement(id, updatedAgreement));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmploymentAgreement> updatePartialAgreement(
            @PathVariable Long id, @RequestBody EmploymentAgreement partialUpdate) {
        return ResponseEntity.ok(service.updatePartialAgreement(id, partialUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAgreement(@PathVariable Long id) {
        service.deleteAgreement(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "EmploymentAgreement with ID " + id + " has been deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmploymentAgreement>> getAllOrFilteredAgreements(
            @RequestParam(required = false) String employeeName,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        if (employeeName == null && role == null && startDate == null && endDate == null) {
            return ResponseEntity.ok(service.getAllAgreements());
        }
        return ResponseEntity.ok(service.listEmploymentAgreements(employeeName, role, startDate, endDate));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmploymentAgreement>> searchEmploymentAgreements(
            @RequestParam Map<String, String> filters) {
        return ResponseEntity.ok(service.searchEmploymentAgreements(filters));
    }
}
