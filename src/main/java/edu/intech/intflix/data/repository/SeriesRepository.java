package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Series;
import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository<Series, Long> {
}
