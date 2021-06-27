package com.birkredit.mapper;

import com.birkredit.controller.credit.dto.CreditResponse;
import com.birkredit.controller.customer.dto.CustomerCreationRequest;
import com.birkredit.controller.customer.dto.CustomerCreditResponse;
import com.birkredit.controller.customer.dto.CustomerLiteResponse;
import com.birkredit.controller.customer.dto.CustomerResponse;
import com.birkredit.controller.payment.dto.PaymentResponse;
import com.birkredit.entity.Credit;
import com.birkredit.entity.Customer;
import com.birkredit.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse customerToCustomerResponse(Customer request);

    Customer customerCreationRequestToCustomer(CustomerCreationRequest request);

    CustomerLiteResponse customerToCustomerLiteResponse(Customer customer);

    @Mapping(target = "credits", source = "credits", qualifiedByName = "creditsToCreditsResponse")
    CustomerCreditResponse customerToCustomerCreditResponse(Customer customer);

    @Named("creditsToCreditsResponse")
    static List<CreditResponse> customerToCustomerCreditResponse(List<Credit> credits) {
        List<CreditResponse> response = new ArrayList<>();

        for (int i = 0; i < credits.size(); i++) {
            Credit credit = credits.get(i);
            CreditResponse creditResponse = new CreditResponse();

            creditResponse.setId(credit.getId());
            creditResponse.setCreditNumber(credit.getCreditNumber());
            creditResponse.setStartDate(credit.getStartDate());
            creditResponse.setEndDate(credit.getEndDate());
            creditResponse.setAmount(credit.getAmount());
            creditResponse.setDebt(credit.getDebt());
            creditResponse.setPercentage(credit.getPercentage());
            creditResponse.setMonths(credit.getMonths());
            creditResponse.setPercentagePerMonth(credit.getPercentagePerMonth());
            creditResponse.setPaymentPerMonth(credit.getPaymentPerMonth());

            for (int j = 0; j < credits.get(i).getPayments().size(); j++) {
                Payment payment = credits.get(i).getPayments().get(j);
                PaymentResponse paymentResponse = new PaymentResponse();

                paymentResponse.setId(payment.getId());
                paymentResponse.setPaymentNumber(payment.getPaymentNumber());
                paymentResponse.setPaymentDate(payment.getPaymentDate());
                paymentResponse.setAmountOfPayment(payment.getAmountOfPayment());
                paymentResponse.setDurationPerMonth(payment.getDurationPerMonth());
                paymentResponse.setMainAmountOfMonth(payment.getMainAmountOfMonth());
                paymentResponse.setInterestAmountOfMonth(payment.getInterestAmountOfMonth());
                paymentResponse.setIsPayed(payment.getIsPayed());

                creditResponse.addPaymentResponse(paymentResponse);
            }

            response.add(creditResponse);
        }

        return response;
    }
}