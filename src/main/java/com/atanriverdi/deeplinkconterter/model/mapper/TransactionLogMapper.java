package com.atanriverdi.deeplinkconterter.model.mapper;

import com.atanriverdi.deeplinkconterter.model.TransactionLogBean;
import com.atanriverdi.deeplinkconterter.model.entity.TransactionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionLogMapper {

    TransactionLog beanToEntity(TransactionLogBean transactionLogDTO);
}
