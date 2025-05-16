package com.spring.hospital.service;

import com.spring.hospital.model.PatientRecord;
import com.spring.hospital.repository.PatientRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientRecordService {

    private final PatientRecordRepository repository;

    public PatientRecordService(PatientRecordRepository repository) {
        this.repository = repository;
    }

    public List<PatientRecord> getRecordsByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    public List<PatientRecord> getAllRecords() {
        return repository.findAll();
    }

    public PatientRecord addRecord(PatientRecord record) {
        return repository.save(record);
    }

    public PatientRecord getRecordById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }

    public PatientRecord updateRecord(Long id, PatientRecord updatedRecord) {
        Optional<PatientRecord> existingRecord = repository.findById(id);

        if (existingRecord.isPresent()) {
            PatientRecord recordToUpdate = existingRecord.get();

            // Updated fields considering inheritance from Person class
            recordToUpdate.setFullName(updatedRecord.getFullName());
            recordToUpdate.setGender(updatedRecord.getGender());
            recordToUpdate.setDateOfBirth(updatedRecord.getDateOfBirth());
            recordToUpdate.setContactNumber(updatedRecord.getContactNumber());
            recordToUpdate.setEmailAddress(updatedRecord.getEmailAddress());
            recordToUpdate.setAddress(updatedRecord.getAddress());
            recordToUpdate.setEmergencyContactName(updatedRecord.getEmergencyContactName());
            recordToUpdate.setEmergencyContactNumber(updatedRecord.getEmergencyContactNumber());
            recordToUpdate.setBloodGroup(updatedRecord.getBloodGroup());
            recordToUpdate.setAllergies(updatedRecord.getAllergies());
            recordToUpdate.setChronicConditions(updatedRecord.getChronicConditions());
            recordToUpdate.setCurrentMedications(updatedRecord.getCurrentMedications());
            recordToUpdate.setDoctorAssigned(updatedRecord.getDoctorAssigned());
            recordToUpdate.setDiagnosisReasonForVisit(updatedRecord.getDiagnosisReasonForVisit());

            return repository.save(recordToUpdate);
        } else {
            return null;
        }
    }
}
