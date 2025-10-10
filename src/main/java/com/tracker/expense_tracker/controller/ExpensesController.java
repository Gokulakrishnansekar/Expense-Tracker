package com.tracker.expense_tracker.controller;

import com.tracker.expense_tracker.model.Expense;
import com.tracker.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpensesController {
    public  final ExpenseService expenseService;
    public ExpensesController( ExpenseService expenseService){
        this.expenseService=expenseService;
    }


    @GetMapping("/expenses")
    public List<Expense> getExpenses(){
        return expenseService.getAllExpense();
    }
    @GetMapping("/expenses/{id}")
    public Expense getExpensesById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }
    @PostMapping("/expenses")
    public Expense getExpenses(@RequestBody Expense e){
        return expenseService.addExpense(e);
    }

}

