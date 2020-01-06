package service.impl;

import domain.Budget;
import domain.BudgetType;
import domain.Category;
import domain.Expenditure;
import dto.BudgetDto;
import org.springframework.stereotype.Service;
import repository.BudgetRepository;
import service.AbstractService;
import service.BudgetService;
import service.CategoryService;
import service.ExpenditureService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BudgetServiceImpl extends AbstractService implements BudgetService {

    public Budget create(BudgetDto budgetDto) {
        Category category = categoryService.findById(budgetDto.getCategoryId());
        return budgetRepository.save(new Budget()
                .setBegin(budgetDto.getBegin())
                .setCategory(category)
                .setType(budgetDto.getBudgetType()))
                .setSize(budgetDto.getSize());
    }
    
    public List<Budget> getByType(final LocalDate currentDate, final BudgetType type) {
        List<Budget> budgets = budgetRepository.findByType(type);
        Stream<Budget> budgetsOptional;
        if (type == BudgetType.MONTHLY)
        {
            budgetsOptional = budgets
                    .stream()
                    .filter(p-> p.getBegin().getMonth().equals(currentDate.getMonth()));
        }
        else if (type == BudgetType.QUARTERLY)
        {
            budgetsOptional = budgets
                    .stream()
                    .filter(p-> p.getBegin().get(IsoFields.QUARTER_OF_YEAR) == currentDate.get(IsoFields.QUARTER_OF_YEAR));
        }
        else
        {
            budgetsOptional = budgets
                    .stream()
                    .filter(p-> p.getBegin().getYear() == currentDate.getYear());
        }
        return budgetsOptional.collect(Collectors.toList());
    }

    public List<Budget> getByCategoryAndType(Long categoryId, BudgetType type) {
        List<Budget> budgets = budgetRepository.findByType(type);
        return budgets.stream()
                .filter(p -> p.getCategory().getId() == categoryId)
                .filter(p -> p.getType() == type).collect(Collectors.toList());
    }

    public Budget edit(BudgetDto budgetDto) {
        return budgetRepository.save(findById(budgetDto.getBudgetId())
                .setBegin(budgetDto.getBegin())
                .setCategory(categoryService.findById(budgetDto.getCategoryId()))
                .setType(budgetDto.getBudgetType()))
                .setSize(budgetDto.getSize());
    }

    public List<Budget> exceededBudgets(Long categoryId)
    {
        List<Budget> budgets = this.budgetRepository.findByCategory(categoryId).get();
        budgets.stream().filter(budget-> {
                    List<Expenditure> expenditures = this.expenditureService.getForBudget(budget.getId());
                    Double sum = expenditures.stream().mapToDouble(o -> o.getExpenditure()).sum();
                    return sum > budget.getSize();
                }
        );
        return budgets;
    }

    public Budget findById(Long budgetId) {
        return this.budgetRepository.findById(budgetId)
                .orElseThrow(() -> new NoSuchElementException(this.i18n.get("budget.no.such.budget", String.valueOf(budgetId))));
    }

    public void deleteByKey(Long budgetId) {
        if (this.budgetRepository.findById(budgetId).isPresent()) {
            this.budgetRepository.deleteById(budgetId);
        } else {
            new NoSuchElementException(this.i18n.get("budget.no.such.budget", String.valueOf(budgetId)));
        }
    }

    @Inject
    protected BudgetRepository budgetRepository;

    @Inject
    protected CategoryService categoryService;

    @Inject
    protected ExpenditureService expenditureService;
}
