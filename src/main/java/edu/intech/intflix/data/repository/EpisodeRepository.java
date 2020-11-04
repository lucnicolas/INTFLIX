package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Episode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends CrudRepository<Episode, Long> {
    Episode findByTitle(String name);
    List<Episode> findAll();
}
