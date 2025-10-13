package com.tracker.expense_tracker.controller;

import com.tracker.expense_tracker.DTO.ExpenseRequestDTO;
import com.tracker.expense_tracker.DTO.ExpenseResponseDTO;
import com.tracker.expense_tracker.Entity.Expense;
import com.tracker.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpensesController {
    public  final ExpenseService expenseService;
    public ExpensesController( ExpenseService expenseService){
        this.expenseService=expenseService;
    }


    @GetMapping("/expenses")
    public List<ExpenseResponseDTO> getExpenses(){
        return expenseService.getAllExpense();
    }
    @GetMapping("/expenses/{id}")
    public ExpenseResponseDTO getExpensesById(@PathVariable Long id){

            return expenseService.getExpenseById(id);



    }
    @PostMapping("/expenses")
    public ExpenseResponseDTO getExpenses(@Valid @RequestBody ExpenseRequestDTO e){
        return expenseService.addExpense(e);
    }

    @PutMapping("/expenses/{id}")
    public ExpenseResponseDTO getExpenses(@Valid @RequestBody ExpenseRequestDTO e,@PathVariable Long id){


        return expenseService.updateExpense(e,id);
    }


}

