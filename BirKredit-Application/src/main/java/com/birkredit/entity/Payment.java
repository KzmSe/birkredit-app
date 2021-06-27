package com.birkredit.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment implements Comparable<Payment>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_number")
    private String paymentNumber;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

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

    @Override
    public int compareTo(Payment payment) {
        if (paymentDate.isEqual(payment.getPaymentDate())) {
            return 0;
        } else if (paymentDate.isBefore(payment.getPaymentDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}
