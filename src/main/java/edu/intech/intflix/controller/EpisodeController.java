package edu.intech.intflix.controller;

import edu.intech.intflix.data.model.Episode;
import edu.intech.intflix.data.repository.EpisodeRepository;
import edu.intech.intflix.exeption.IdMismatchException;
import edu.intech.intflix.exeption.InvalidEntryException;
import edu.intech.intflix.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/api/episodes") // This means URL's start with /api (after Application path)
public class EpisodeController {
    @Autowired  // This means to get the bean called EpisodeRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EpisodeRepository repository;

    /**
     * Get all episodes
     * @return a list of Episodes
     */
    @GetMapping // Map ONLY GET Requests
    public List<Episode> findAll() {
        // This returns a JSON or XML with the episodes
        return repository.findAll();
    }

    /**
     * Get episode by name
     * @param episodeTitle
     * @return called episode
     */
    @GetMapping("/title/{episodeTitle}") // Map ONLY GET Requests
    public Episode findByTitle(@PathVariable String episodeTitle) {
        // @PathVariable means it is a parameter from path
        return repository.findByTitle(episodeTitle);
    }

    /**
     * Get Episode by id
     * @param id
     * @return called episode
     * @throws NotFoundException
     */
    @GetMapping("/{id}") // Map ONLY GET Requests
    public Episode findOne(@PathVariable Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Create a new episode
     * @param episode
     * @return created episode
     */
    @PostMapping // Map ONLY POST Requests
    @ResponseStatus(HttpStatus.CREATED)
    public Episode create(@RequestBody Episode episode) throws InvalidEntryException {
        if (episode.getNumber() > 0) {
            return repository.save(episode);
        } else {
            throw new InvalidEntryException();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        repository.findById(id)
                .orElseThrow(NotFoundException::new);
        repository.deleteById(id);
    }

    /**
     * Delete all episodes
     */
    @DeleteMapping // Map ONLY DELETE Requests
    public void delete() {
        repository.deleteAll();
    }

    /**
     * Update sent episode
     * @param episode Episode object
     * @param id Episode id
     * @return saved episode
     * @throws IdMismatchException
     * @throws NotFoundException
     */
    @PutMapping("/{id}") // Map ONLY PUT Requests
    public Episode updateEpisode(@RequestBody Episode episode, @PathVariable Long id) throws IdMismatchException, NotFoundException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if (episode.getId() != id) {
            throw new IdMismatchException();
        }
        repository.findById(id)
                .orElseThrow(NotFoundException::new);
        return repository.save(episode);
    }
}