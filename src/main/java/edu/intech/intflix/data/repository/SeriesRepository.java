package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long> {
    Series findByName(String name);
    List<Series> findAll();
}
