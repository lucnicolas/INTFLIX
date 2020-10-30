package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Season;
import edu.intech.intflix.data.model.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends CrudRepository<Season, Long> {
    Season findByNumber(int number);
    List<Season> findAll();
}
