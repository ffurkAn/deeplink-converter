package com.atanriverdi.deeplinkconterter.repository;

import com.atanriverdi.deeplinkconterter.model.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, String> {
}
