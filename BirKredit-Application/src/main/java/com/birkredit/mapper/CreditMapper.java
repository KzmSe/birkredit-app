package com.birkredit.mapper;

import com.birkredit.controller.credit.dto.CreditRequest;
import com.birkredit.controller.credit.dto.CreditResponse;
import com.birkredit.controller.payment.dto.PaymentResponse;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

@Mapper
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "amount", source = "amount", qualifiedByName = "roundDouble")
    @Mapping(target = "debt", source = "debt", qualifiedByName = "roundDouble")
    @Mapping(target = "percentagePerMonth", source = "percentagePerMonth", qualifiedByName = "roundDouble")
    @Mapping(target = "paymentPerMonth", source = "paymentPerMonth", qualifiedByName = "roundDouble")
    @Mapping(target = "payments", source = "payments", qualifiedByName = "paymentsToPaymentResponse")
    CreditResponse creditToCreditResponse(Credit credit);

    Credit creditRequestToCredit(CreditRequest request);

    @Named("paymentsToPaymentResponse")
    static List<PaymentResponse> paymentsToPaymentResponse(SortedSet<Payment> payments) {
        List<PaymentResponse> response = new ArrayList<>();
        DecimalFormat formatter = new DecimalFormat("#.##");

        for (Payment payment : payments) {
            PaymentResponse paymentResponse = new PaymentResponse();

            paymentResponse.setId(payment.getId());
            paymentResponse.setPaymentNumber(payment.getPaymentNumber());
            paymentResponse.setPaymentDate(payment.getPaymentDate());
            paymentResponse.setAmountOfPayment(payment.getAmountOfPayment());
            paymentResponse.setDurationPerMonth(payment.getDurationPerMonth());
            paymentResponse.setMainAmountOfMonth(Double.valueOf(formatter.format(payment.getMainAmountOfMonth())));
            paymentResponse.setInterestAmountOfMonth(Double.valueOf(formatter.format(payment.getInterestAmountOfMonth())));
            paymentResponse.setIsPayed(payment.getIsPayed());

            response.add(paymentResponse);
        }

        return response;
    }

    @Named("roundDouble")
    static Double roundDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }
}