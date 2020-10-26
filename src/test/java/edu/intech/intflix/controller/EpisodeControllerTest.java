package edu.intech.intflix.controller;

import edu.intech.intflix.data.model.Episode;
import edu.intech.intflix.data.repository.EpisodeRepository;
import edu.intech.intflix.utils.JsonUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EpisodeControllerTest {

    @Autowired // Inject MockMvc
    private MockMvc mvc;

    @Autowired // Inject repository
    private EpisodeRepository repository;

    private static final String API_ROOT = "/api/episodes";

    /**
     * After each test, clear data
     */
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    /**
     * Check if we can get created Episode when getting all Episodes
     * @throws Exception
     */
    @Test
    public void givenEpisodes_whenGetEpisodes_thenStatus200() throws Exception {
        String episodeTitle = randomAlphabetic(10);
        Episode episode = new Episode();
        episode.setTitle(episodeTitle);
        repository.save(episode);
        mvc.perform(get("/api/episodes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is(episodeTitle)));
    }

    /**
     * Check if get all Episodes works
     * @throws Exception
     */
    @Test
    public void whenGetAllEpisodes_thenOK() throws Exception {
        final Episode episodeTest1 = createTestEpisode();
        final Episode episodeTest2 = createTestEpisode();
        mvc.perform(get(API_ROOT).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("*", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].title", is(episodeTest1.getTitle())))
                .andExpect(jsonPath("$[1].title", is(episodeTest2.getTitle())));
    }

    /**
     * Check if get a Episode by name works
     * @throws Exception
     */
    @Test
    public void whenGetEpisodesByName_thenOK() throws Exception {
        final Episode episodeTest = createTestEpisode();
        mvc.perform(get(API_ROOT + "/title/" + episodeTest.getTitle()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))));
    }


    /**
     * Check if get a Episode by id works
     * @throws Exception
     */
    @Test
    public void whenGetCreatedEpisodeById_thenOK() throws Exception {
        final Episode episodeTest = createTestEpisode();
        mvc.perform(get(API_ROOT + "/" + episodeTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.title", is(episodeTest.getTitle())));
    }

    /**
     * Check that get not existing Episode return 404 code
     * @throws Exception
     */
    @Test
    public void whenGetNotExistEpisodeById_thenNotFound() throws Exception {
        mvc.perform(get(API_ROOT + "/" + randomNumeric(4)))
                .andExpect(status().isNotFound());
    }

    /**
     * Check that when creating Episode return CREATED code
     * @throws Exception
     */
    // POST
    @Test
    public void whenCreateNewEpisode_thenCreated() throws Exception {
        Episode episodeToCreate = new Episode();
        episodeToCreate.setTitle(randomAlphabetic(10));
        episodeToCreate.setNumber(Integer.parseInt(randomNumeric(4)));
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(episodeToCreate)))
                .andExpect(status().isCreated());
    }

    /**
     * Check that when creating invalid Episode return 400 code
     * @throws Exception
     */
    @Test
    public void whenInvalidEpisode_thenError() throws Exception {
        Episode episodeToCreate = new Episode();
        episodeToCreate.setTitle(null);
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(episodeToCreate)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Check if updating Episode works
     * @throws Exception
     */
    @Test
    public void whenUpdateCreatedEpisode_thenUpdated() throws Exception {
        final Episode episodeTest = createTestEpisode();
        episodeTest.setTitle(randomAlphabetic(10));
        mvc.perform(put(API_ROOT + "/" + episodeTest.getId()).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(episodeTest)))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + episodeTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.title", is(episodeTest.getTitle())));
    }

    /**
     * Check if deleting Episode works
     * @throws Exception
     */
    @Test
    public void whenDeleteCreatedEpisode_thenOk() throws Exception {
        final Episode episodeTest = createTestEpisode();
        mvc.perform(delete(API_ROOT + "/" + episodeTest.getId()))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + episodeTest.getId()))
                .andExpect(status().isNotFound());
    }

    /**
     * Generate a Episode with a random name
     * @return Episode created
     */
    private Episode createTestEpisode() {
        Episode episode = new Episode();
        episode.setTitle(randomAlphabetic(20));
        repository.save(episode);
        return repository.findByTitle(episode.getTitle());
    }
}