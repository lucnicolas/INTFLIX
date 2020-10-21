package edu.intech.intflix.controller;

import edu.intech.intflix.data.model.Season;
import edu.intech.intflix.data.repository.SeasonRepository;
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
class SeasonControllerTest {

    @Autowired // Inject MockMvc
    private MockMvc mvc;

    @Autowired // Inject repository
    private SeasonRepository repository;

    private static final String API_ROOT = "/api/seasons";

    /**
     * After each test, clear data
     */
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    /**
     * Check if we can get created Season when getting all Seasons
     * @throws Exception
     */
    @Test
    public void givenSeasons_whenGetSeasons_thenStatus200() throws Exception {
        int seasonNumber = Integer.parseInt(randomNumeric(4));
        Season season = new Season();
        season.setNumber(seasonNumber);
        repository.save(season);
        mvc.perform(get("/api/seasons")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number", is(seasonNumber)));
    }

    /**
     * Check if get all Seasons works
     * @throws Exception
     */
    @Test
    public void whenGetAllSeasons_thenOK() throws Exception {
        final Season seasonTest1 = createTestSeason();
        final Season seasonTest2 = createTestSeason();
        mvc.perform(get(API_ROOT).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("*", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].number", is(seasonTest1.getNumber())))
                .andExpect(jsonPath("$[1].number", is(seasonTest2.getNumber())));
    }

    /**
     * Check if get a Season by name works
     * @throws Exception
     */
    @Test
    public void whenGetSeasonsByName_thenOK() throws Exception {
        final Season seasonTest = createTestSeason();
        mvc.perform(get(API_ROOT + "/number/" + seasonTest.getNumber()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))));
    }


    /**
     * Check if get a Season by id works
     * @throws Exception
     */
    @Test
    public void whenGetCreatedSeasonById_thenOK() throws Exception {
        final Season seasonTest = createTestSeason();
        mvc.perform(get(API_ROOT + "/" + seasonTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.number", is(seasonTest.getNumber())));
    }

    /**
     * Check that get not existing Season return 404 code
     * @throws Exception
     */
    @Test
    public void whenGetNotExistSeasonById_thenNotFound() throws Exception {
        mvc.perform(get(API_ROOT + "/" + randomNumeric(4)))
                .andExpect(status().isNotFound());
    }

    /**
     * Check that when creating Season return CREATED code
     * @throws Exception
     */
    // POST
    @Test
    public void whenCreateNewSeason_thenCreated() throws Exception {
        Season seasonToCreate = new Season();
        seasonToCreate.setNumber(Integer.parseInt(randomNumeric(4)));
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seasonToCreate)))
                .andExpect(status().isCreated());
    }

    /**
     * Check that when creating invalid Season return 400 code
     * @throws Exception
     */
    @Test
    public void whenInvalidSeason_thenError() throws Exception {
        Season seasonToCreate = new Season();
        seasonToCreate.setNumber(-1);
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seasonToCreate)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Check if updating Season works
     * @throws Exception
     */
    @Test
    public void whenUpdateCreatedSeason_thenUpdated() throws Exception {
        final Season seasonTest = createTestSeason();
        seasonTest.setNumber(Integer.parseInt(randomNumeric(4)));
        mvc.perform(put(API_ROOT + "/" + seasonTest.getId()).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seasonTest)))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + seasonTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.number", is(seasonTest.getNumber())));
    }

    /**
     * Check if deleting Season works
     * @throws Exception
     */
    @Test
    public void whenDeleteCreatedSeason_thenOk() throws Exception {
        final Season seasonTest = createTestSeason();
        mvc.perform(delete(API_ROOT + "/" + seasonTest.getId()))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + seasonTest.getId()))
                .andExpect(status().isNotFound());
    }

    /**
     * Generate a Season with a random name
     * @return Season created
     */
    private Season createTestSeason() {
        Season season = new Season();
        season.setNumber(Integer.parseInt(randomNumeric(4)));
        repository.save(season);
        return repository.findByNumber(season.getNumber());
    }
}