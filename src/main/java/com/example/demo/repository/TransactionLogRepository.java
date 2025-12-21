package com.example.demo.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.TransactionLog;
import com.example.demo.entity.User;
@Repository
public interface TransactionLogRepository
        extends JpaRepository<TransactionLog, Long> {
    List<TransactionLog> findByUser(User user);
    List<TransactionLog> findByUserAndTransactionDateBetween(
            User user,
            LocalDate start,
            LocalDate end
    );
}
