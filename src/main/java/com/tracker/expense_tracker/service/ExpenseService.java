package com.tracker.expense_tracker.service;

import com.tracker.expense_tracker.DTO.ExpenseRequestDTO;
import com.tracker.expense_tracker.DTO.ExpenseResponseDTO;
import com.tracker.expense_tracker.DTO.PageView;
import com.tracker.expense_tracker.exception_handling.UserNotFoundException;
import com.tracker.expense_tracker.Entity.Expense;
import com.tracker.expense_tracker.model.AmountByCategory;
import com.tracker.expense_tracker.repository.ExpensesRepository;
import com.tracker.expense_tracker.specification.ExpenseSpecification;
import com.tracker.expense_tracker.specification.ExpenseSpecificationFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {


//    Expense e1=new Expense(1L,"name",100, LocalDate.now(),"furniture","gokul");
//    Expense e2=new Expense(2L,"two",200, LocalDate.now(),"toys","abi");
//    public Map<Long,Expense> expenseRepo=new HashMap<>();

    ExpensesRepository expensesRepository;
    public ExpenseService(ExpensesRepository expensesRepository){
        this.expensesRepository=expensesRepository;
//        expenseRepo.put(1L,e1);
//        expenseRepo.put(2L,e2);
      //  expensesRepository
    }

    public PageView<ExpenseResponseDTO> convertPageDateToPageObject(Page<Expense> data){
            PageView<ExpenseResponseDTO> pv=new PageView<>();
            pv.setItems(data.getContent().stream().map(this::convertToExpenseDTO).toList());
            pv.setSize(data.getSize());
            pv.setHasNextPage(data.hasNext());
            pv.setTotalElements(data.getTotalElements());
        pv.setCurrentPageElements(data.getNumberOfElements());
           return pv;
    }

    public List<ExpenseResponseDTO> convertToExpensesDTO(List<Expense> ex)
    {

      return  ex.stream().map((expense)->{
          ExpenseResponseDTO dto=new ExpenseResponseDTO();
            dto.setId(expense.getId());
            dto.setCategory(expense.getCategory());
            dto.setTitle(expense.getTitle());
            dto.setUserName(expense.getUserName());
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            return dto;
      }).toList();
    }
    public ExpenseResponseDTO convertToExpenseDTO(Expense expense)
    {
        ExpenseResponseDTO dto=new ExpenseResponseDTO();

            dto.setId(expense.getId());
            dto.setCategory(expense.getCategory());
            dto.setTitle(expense.getTitle());
            dto.setUserName(expense.getUserName());
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            return dto;

    }
    public Expense convertToExpenseEntity(ExpenseRequestDTO expenseDto)
    {
        Expense expense=new Expense();
        expense.setCategory(expenseDto.getCategory());
        expense.setTitle(expenseDto.getTitle());
        expense.setUserName(expenseDto.getUserName());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
           return expense;
    }



    public PageView<ExpenseResponseDTO> getAllExpense(ExpenseSpecificationFilter expenseSpecificationFilter, Pageable p){
        Specification<Expense> spe=ExpenseSpecification.filterBy(expenseSpecificationFilter);
        Page<Expense> data=expensesRepository.findAll(spe,p);
       return  this.convertPageDateToPageObject(data);



    }

    public ExpenseResponseDTO getExpenseById(Long l)
    {

       Expense e=  expensesRepository.findById(l).orElseThrow(UserNotFoundException::new);
         return  convertToExpenseDTO(e);
    }

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO e){
        try{

          Expense saved=  expensesRepository.save(convertToExpenseEntity(e));
            return this.convertToExpenseDTO(saved);
        }
        catch (Exception exception){
            System.out.println("error while saving");
            return null;
        }

    }

    public ExpenseResponseDTO updateExpense(ExpenseRequestDTO e,Long id){

             expensesRepository.findById(id).orElseThrow(UserNotFoundException::new);
             Expense expense=this.convertToExpenseEntity(e);
             expense.setId(id);
             Expense saved=  expensesRepository.save(expense);
             return this.convertToExpenseDTO(saved);



    }

    public List<AmountByCategory> getAmountByCategory(String userName, LocalDate startDate,LocalDate endDate){
        return expensesRepository.getAmountByCategory(userName,startDate,endDate);
    }



}
