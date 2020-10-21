package edu.intech.intflix.data.repository;

import edu.intech.intflix.data.model.Episode;
import org.springframework.data.repository.CrudRepository;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
}
