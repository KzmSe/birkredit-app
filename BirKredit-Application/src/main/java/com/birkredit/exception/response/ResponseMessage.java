package com.birkredit.exception.response;

public class ResponseMessage {
    public final static String ERROR_CUSTOMER_NOT_FOUND_BY_ID = "Customer not found with given id.";
    public final static String ERROR_CUSTOMER_NOT_FOUND_BY_CUSTOMER_NUMBER = "Customer not found with given customer number.";
    public final static String ERROR_CREDIT_NOT_FOUND_BY_CUSTOMER_NUMBER_OR_CREDIT_NUMBER = "Credit not found with given customer number or credit number.";
    public final static String ERROR_MIN_AMOUNT_OF_PAYMENT = "Min amount of the payment should be greater than 1 azn.";

    public final static String ERROR_INTERNAL_SERVER_ERROR = "Internal server error.";
}
