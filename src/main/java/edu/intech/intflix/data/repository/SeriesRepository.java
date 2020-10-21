package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Series;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeriesRepository extends CrudRepository<Series, Long> {
    Series findByName(String name);
    List<Series> findAll();
}
