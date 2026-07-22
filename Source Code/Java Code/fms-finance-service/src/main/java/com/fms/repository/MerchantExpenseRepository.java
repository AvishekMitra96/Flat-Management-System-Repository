package com.fms.repository;

import com.fms.entity.MerchantExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MerchantExpenseRepository extends JpaRepository<MerchantExpense, Long> {
    List<MerchantExpense> findByStatus(MerchantExpense.ExpenseStatus status);
}
