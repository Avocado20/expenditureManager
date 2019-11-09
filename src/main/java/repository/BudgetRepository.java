package repository;

import domain.Budget;
import domain.BudgetType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends PagingAndSortingRepository<Budget, Long>{

    Optional<List<Budget>> findByCategory(Long categoryId);

    Optional<Budget> findById(Long aLong);

    List<Budget> findByType(BudgetType type);
}
