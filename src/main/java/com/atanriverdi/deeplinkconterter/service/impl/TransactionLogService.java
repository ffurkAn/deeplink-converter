package com.atanriverdi.deeplinkconterter.service.impl;

import com.atanriverdi.deeplinkconterter.model.TransactionLogBean;
import com.atanriverdi.deeplinkconterter.model.entity.TransactionLog;
import com.atanriverdi.deeplinkconterter.model.mapper.TransactionLogMapper;
import com.atanriverdi.deeplinkconterter.repository.TransactionLogRepository;
import com.atanriverdi.deeplinkconterter.service.ITransactionLogService;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogService implements ITransactionLogService {

    private TransactionLogRepository transactionLogRepository;

    private TransactionLogMapper transactionLogMapper;

    private TransactionLogBean transactionLogBean;

    public TransactionLogService(TransactionLogRepository transactionLogRepository, TransactionLogMapper transactionLogMapper, TransactionLogBean transactionLogBean) {
        this.transactionLogRepository = transactionLogRepository;
        this.transactionLogMapper = transactionLogMapper;
        this.transactionLogBean = transactionLogBean;
    }

    @Override
    public TransactionLog save() {

        return transactionLogRepository.save(transactionLogMapper.beanToEntity(transactionLogBean));
    }
}
