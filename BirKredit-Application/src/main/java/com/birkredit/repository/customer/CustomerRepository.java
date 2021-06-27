package com.birkredit.repository.customer;

import com.birkredit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<Customer> findByCustomerNumber(String customerNumber);

    Optional<Customer> findByCustomerNumberAndCredits_CreditNumber(String customerNumber, String creditNumber);
}
