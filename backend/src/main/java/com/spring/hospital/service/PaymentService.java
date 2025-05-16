package com.spring.hospital.service;

import com.spring.hospital.model.Payment;
import com.spring.hospital.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Create or Update Payment
    public Payment saveOrUpdatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Get Payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Get all Payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Delete Payment by ID
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
