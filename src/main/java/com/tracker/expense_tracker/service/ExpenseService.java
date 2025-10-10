package com.tracker.expense_tracker.service;

import com.tracker.expense_tracker.model.Expense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    Expense e1=new Expense(1L,"name",100, LocalDate.now(),"furniture","gokul");
    Expense e2=new Expense(2L,"two",200, LocalDate.now(),"toys","abi");
    public Map<Long,Expense> expenseRepo=new HashMap<>();

    public ExpenseService(){
        expenseRepo.put(1L,e1);
        expenseRepo.put(2L,e2);
    }


    public List<Expense> getAllExpense(){
        return new ArrayList<Expense>(expenseRepo.values());
    }

    public Expense getExpenseById(Long l)
    {
        return expenseRepo.get(l);
    }

    public Expense addExpense(Expense e){
        e.setId(expenseRepo.size()+1L);
        expenseRepo.put(e.getId(),e);
        return expenseRepo.get(e.getId());
    }



}
