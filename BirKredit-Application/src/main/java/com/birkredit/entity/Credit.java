package com.birkredit.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "credit")
@Getter
@Setter
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
    @OrderBy("paymentDate asc")
    private SortedSet<Payment> payments = new TreeSet<>();

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    public void addPayment(Payment payment) {
        payment.setCredit(this);
        this.payments.add(payment);
    }
}
