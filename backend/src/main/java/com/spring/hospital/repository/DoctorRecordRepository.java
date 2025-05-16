package com.spring.hospital.repository;

import com.spring.hospital.model.DoctorRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRecordRepository extends JpaRepository<DoctorRecord, Long> {
    List<DoctorRecord> findByDoctorId(Long doctorId);
}
