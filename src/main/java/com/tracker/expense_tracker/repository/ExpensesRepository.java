package com.tracker.expense_tracker.repository;

import com.tracker.expense_tracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense,Long> {


    @Query("select e from Expense e order by e.id")
    @Override
    public List<Expense> findAll();
}
