package repository;

import domain.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
}
