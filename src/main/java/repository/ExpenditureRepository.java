package repository;

import domain.Expenditure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenditureRepository extends PagingAndSortingRepository<Expenditure, Long> {
    List<Expenditure> findByCategory(Long categoryId);
    List<Expenditure> findAll();
}
