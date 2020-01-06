package service.impl;

import domain.Budget;
import domain.BudgetType;
import domain.Category;
import domain.Expenditure;
import dto.ExpenditureDto;
import org.springframework.stereotype.Service;
import repository.ExpenditureRepository;
import service.AbstractService;
import service.BudgetService;
import service.CategoryService;
import service.ExpenditureService;

import javax.inject.Inject;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ExpenditureServiceImpl extends AbstractService implements ExpenditureService {

    @Override
    public List<Expenditure> getForBudget(Long budgetId) {
        Budget budget = budgetService.findById(budgetId);

        List<Expenditure> expenditures = getForCategory(budget.getCategory().getId());
        return expenditures.stream().filter(p->
                p.getTime().isAfter(ChronoLocalDateTime.from(budget.getBegin().plusMonths(this.getBugdetTypeMonths(budget))))
        ).collect(Collectors.toList());
    }

    @Override
    public List<Expenditure> getForCategory(Long categoryId) {
        return expenditureRepository.findByCategory(categoryId);
    }

    @Override
    public List<Expenditure> getLast(int howMany) {
        return expenditureRepository.findAll().subList(0, howMany);
    }

    @Override
    public List<Expenditure> getLast() {
        return getLast(10);
    }

    @Override
    public Expenditure create(ExpenditureDto expenditureDto) {

        Expenditure expenditure = new Expenditure();
        Category category = categoryService.findById(expenditureDto.getCategoryId());

        expenditure.setCategory(category)
                .setTime(expenditureDto.getTime())
                .setExpenditure(expenditureDto.getExpenditure());

        return expenditureRepository.save(expenditure);
    }

    @Override
    public Expenditure edit(ExpenditureDto expenditureDto) {
        Expenditure expenditure = expenditureRepository.findById(expenditureDto.getId()).orElseThrow(
                () -> new NoSuchElementException(this.i18n.get("expenditure.no.such.expenditure", String.valueOf(expenditureDto.getId())))
        );
        Category category = categoryService.findById(expenditureDto.getCategoryId());
        expenditure.setCategory(category)
                .setTime(expenditureDto.getTime())
                .setExpenditure(expenditureDto.getExpenditure());
        return expenditureRepository.save(expenditure);
    }

    @Override
    public void delete(Long expenditureId) {
        expenditureRepository.findById(expenditureId)
                .orElseThrow(() -> new NoSuchElementException(
                        this.i18n.get("expenditure.no.such.expenditure", String.valueOf(expenditureId))));
        expenditureRepository.deleteById(expenditureId);
    }

    private int getBugdetTypeMonths(Budget budget)
    {
        if (budget.getType() == BudgetType.MONTHLY)
        {
            return 1;
        } else if (budget.getType() == BudgetType.QUARTERLY)
        {
            return 3;
        }
        else
        {
            return 12;
        }
    }

    @Inject
    protected BudgetService budgetService;

    @Inject
    CategoryService categoryService;

    @Inject
    protected ExpenditureRepository expenditureRepository;
}
