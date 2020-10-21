package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Episode;
import edu.intech.intflix.data.model.Series;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
    Episode findByTitle(String name);
    List<Episode> findAll();
}
