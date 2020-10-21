package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Season;
import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season, Long> {
}
