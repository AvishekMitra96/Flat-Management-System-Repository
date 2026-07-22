package com.fms.service;

import com.fms.entity.MaintenanceDue;
import com.fms.entity.MerchantExpense;
import com.fms.repository.MaintenanceDueRepository;
import com.fms.repository.MerchantExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final MaintenanceDueRepository maintenanceDueRepository;
    private final MerchantExpenseRepository merchantExpenseRepository;

    public List<MaintenanceDue> getDuesForFlat(Long flatId) {
        return maintenanceDueRepository.findByFlatId(flatId);
    }

    @Transactional
    public MaintenanceDue payDue(Long dueId, String razorpayPaymentId) {
        // Logic to verify razorpay signature would go here
        MaintenanceDue due = maintenanceDueRepository.findById(dueId).orElseThrow();
        if (due.getStatus() == MaintenanceDue.Status.PAID) {
            throw new RuntimeException("Due is already paid");
        }
        due.setStatus(MaintenanceDue.Status.PAID);
        return maintenanceDueRepository.save(due);
    }

    public MerchantExpense raiseExpense(MerchantExpense expense) {
        expense.setStatus(MerchantExpense.ExpenseStatus.RAISED);
        expense.setRaisedAt(LocalDateTime.now());
        return merchantExpenseRepository.save(expense);
    }

    @Transactional
    public MerchantExpense approveExpense(Long expenseId) {
        MerchantExpense expense = merchantExpenseRepository.findById(expenseId).orElseThrow();
        if (expense.getStatus() != MerchantExpense.ExpenseStatus.RAISED) {
            throw new RuntimeException("Expense is not in RAISED state");
        }
        expense.setStatus(MerchantExpense.ExpenseStatus.APPROVED);
        expense.setApprovedAt(LocalDateTime.now());
        return merchantExpenseRepository.save(expense);
    }

    @Transactional
    public MerchantExpense processExpense(Long expenseId) {
        MerchantExpense expense = merchantExpenseRepository.findById(expenseId).orElseThrow();
        if (expense.getStatus() != MerchantExpense.ExpenseStatus.APPROVED) {
            throw new RuntimeException("Expense must be APPROVED first");
        }
        expense.setStatus(MerchantExpense.ExpenseStatus.PROCESSED);
        expense.setProcessedAt(LocalDateTime.now());
        return merchantExpenseRepository.save(expense);
    }

    public String fetchRealTimeBankBalance() {
        // Integrate with a dummy Banking API here
        return "Dummy Bank Balance: INR 5,00,000";
    }
}
