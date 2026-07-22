package com.fms.controller;

import com.fms.entity.MaintenanceDue;
import com.fms.entity.MerchantExpense;
import com.fms.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping("/dues/{flatId}")
    public ResponseEntity<?> getDues(@PathVariable Long flatId) {
        return ResponseEntity.ok(financeService.getDuesForFlat(flatId));
    }

    @PostMapping("/dues/pay/{dueId}")
    public ResponseEntity<?> payDue(@PathVariable Long dueId, @RequestParam String paymentId) {
        try {
            return ResponseEntity.ok(financeService.payDue(dueId, paymentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/expense/raise")
    public ResponseEntity<?> raiseExpense(@RequestBody MerchantExpense expense) {
        return ResponseEntity.ok(financeService.raiseExpense(expense));
    }

    @PostMapping("/expense/approve/{expenseId}")
    public ResponseEntity<?> approveExpense(@PathVariable Long expenseId) {
        return ResponseEntity.ok(financeService.approveExpense(expenseId));
    }

    @PostMapping("/expense/process/{expenseId}")
    public ResponseEntity<?> processExpense(@PathVariable Long expenseId) {
        return ResponseEntity.ok(financeService.processExpense(expenseId));
    }

    @GetMapping("/bank/balance")
    public ResponseEntity<?> getBankBalance() {
        return ResponseEntity.ok(financeService.fetchRealTimeBankBalance());
    }
}
