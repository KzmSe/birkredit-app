package com.birkredit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "credit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "credit_number")
    private String creditNumber;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "debt")
    private Double debt;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "months")
    private Double months;

    @Column(name = "percentage_per_month")
    private Double percentagePerMonth;

    @Column(name = "payment_per_month")
    private Double paymentPerMonth;

    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    public void addPayment(Payment payment) {
        payment.setCredit(this);
        this.payments.add(payment);
    }
}
