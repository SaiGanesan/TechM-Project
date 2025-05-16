package com.spring.hospital.service;

import com.spring.hospital.model.DoctorRecord;
import com.spring.hospital.repository.DoctorRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorRecordService {

    private final DoctorRecordRepository doctorRecordRepository;

    public DoctorRecordService(DoctorRecordRepository doctorRecordRepository) {
        this.doctorRecordRepository = doctorRecordRepository;
    }

    public DoctorRecord createDoctor(DoctorRecord doctorRecord) {
        return doctorRecordRepository.save(doctorRecord);
    }

    public List<DoctorRecord> getAllDoctors() {
        return doctorRecordRepository.findAll();
    }

    public DoctorRecord getDoctorById(Long id) {
        return doctorRecordRepository.findById(id).orElse(null);
    }

    public DoctorRecord updateDoctor(Long id, DoctorRecord updatedRecord) {
        return doctorRecordRepository.findById(id).map(existing -> {
            updatedRecord.setId(id);
            return doctorRecordRepository.save(updatedRecord);
        }).orElse(null);
    }

    public void deleteDoctor(Long id) {
        doctorRecordRepository.deleteById(id);
    }

    // 🔧 ADD THIS METHOD
    public List<DoctorRecord> getRecordsByDoctorId(Long doctorId) {
        return doctorRecordRepository.findByDoctorId(doctorId);
    }
}
