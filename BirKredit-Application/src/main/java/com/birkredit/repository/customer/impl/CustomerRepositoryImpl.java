package com.birkredit.repository.customer.impl;

import com.birkredit.entity.Customer;
import com.birkredit.repository.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Customer> findByCustomerNumberAndCreditNumber(String customerNumber, String creditNumber) {
        TypedQuery<Customer> typedQuery = entityManager
                .createQuery(
                        "SELECT cu FROM Customer cu JOIN FETCH Credit cr WHERE cu.customerNumber = :customerNumber AND cr.creditNumber = :creditNumber", Customer.class);

        typedQuery.setParameter("customerNumber", customerNumber);
        typedQuery.setParameter("creditNumber", creditNumber);

        Customer customer = typedQuery.getSingleResult();
        return Optional.of(customer);
    }
}
