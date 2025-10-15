package com.tracker.expense_tracker.controller;

import com.tracker.expense_tracker.DTO.ExpenseRequestDTO;
import com.tracker.expense_tracker.DTO.ExpenseResponseDTO;
import com.tracker.expense_tracker.Entity.Expense;
import com.tracker.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
public class ExpensesController {
    public  final ExpenseService expenseService;
    public ExpensesController( ExpenseService expenseService){
        this.expenseService=expenseService;
    }


    @GetMapping("/expenses")
    public Page<ExpenseResponseDTO> getExpenses(
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false ,defaultValue = "id") String sortBy,
            @RequestParam(required = false , defaultValue = "asc") String sortDirection

    ){
        Sort s=null;
        if(sortDirection.equalsIgnoreCase("ASC"))
        {
            s=Sort.by(sortBy).ascending();
        }
        else if(sortDirection.equalsIgnoreCase("DESC")){
            s=Sort.by(sortBy).descending();
        }
        assert s != null;
        Pageable p= PageRequest.of(page-1,size,s);
        return expenseService.getAllExpense(p);
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

