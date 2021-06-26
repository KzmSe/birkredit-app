package com.birkredit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_number")
    private String paymentNumber;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "amount_of_payment")
    private Double amountOfPayment;

    @Column(name = "duration_per_month")
    private Double durationPerMonth;

    @Column(name = "main_amount_of_month")
    private Double mainAmountOfMonth;

    @Column(name = "interest_amount_of_month")
    private Double interestAmountOfMonth;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @ManyToOne
    @JoinColumn(name = "fk_credit_id")
    private Credit credit;
}
