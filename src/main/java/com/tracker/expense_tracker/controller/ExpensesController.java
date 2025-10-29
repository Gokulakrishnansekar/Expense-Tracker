package com.tracker.expense_tracker.controller;

import com.tracker.expense_tracker.DTO.ExpenseRequestDTO;
import com.tracker.expense_tracker.DTO.ExpenseResponseDTO;
import com.tracker.expense_tracker.DTO.PageView;
import com.tracker.expense_tracker.Entity.Expense;
import com.tracker.expense_tracker.model.AmountByCategory;
import com.tracker.expense_tracker.service.ExpenseService;
import com.tracker.expense_tracker.specification.ExpenseSpecificationFilter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ExpensesController {
    public  final ExpenseService expenseService;
    public ExpensesController( ExpenseService expenseService){
        this.expenseService=expenseService;
    }

    //@Cacheable("expensesCache")-->if key is not needed
    @GetMapping("/expenses")
    @Operation(summary = "get Expenses",description = "Getting all Expenses")
    public PageView<ExpenseResponseDTO> getExpenses(
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false ,defaultValue = "id") String sortBy,
            @RequestParam(required = false , defaultValue = "asc") String sortDirection,
            @ModelAttribute ExpenseSpecificationFilter expenseSpecificationFilter
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
        return expenseService.getAllExpense(expenseSpecificationFilter,p);
    }


    @Cacheable(value = "expense" ,key = "#id")
    @GetMapping("/expenses/{id}")
    public ExpenseResponseDTO getExpensesById(@PathVariable Long id){
            return expenseService.getExpenseById(id);
    }
    @PostMapping("/expenses")
    public ExpenseResponseDTO getExpenses(@Valid @RequestBody ExpenseRequestDTO e){
        return expenseService.addExpense(e);
    }

    @CachePut(key = "#id",value = "expense")
    @PutMapping("/expenses/{id}")
    public ExpenseResponseDTO getExpenses(@Valid @RequestBody ExpenseRequestDTO e,@PathVariable Long id){


        return expenseService.updateExpense(e,id);
    }


    @GetMapping("/summary/category")
    public List<AmountByCategory> GetAmountByCategory(@RequestParam(required = false) String userName,
                                                      @RequestParam(required = false)
                                                      @DateTimeFormat LocalDate startDate,
                                                      @RequestParam(required = false)
                                                      @DateTimeFormat LocalDate endDate

                                                      ){
            return expenseService.getAmountByCategory(userName,startDate,endDate);
    }


}

