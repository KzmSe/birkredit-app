package com.birkredit.mapper;

import com.birkredit.controller.dto.CreditRequest;
import com.birkredit.controller.dto.CreditResponse;
import com.birkredit.entity.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    CreditResponse creditToCreditResponse(Credit credit);

    Credit creditRequestToCredit(CreditRequest request);
}