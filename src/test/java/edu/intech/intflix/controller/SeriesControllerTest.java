package edu.intech.intflix.controller;

import edu.intech.intflix.data.model.Series;
import edu.intech.intflix.data.repository.SeriesRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SeriesControllerTest {

    @Autowired // Inject MockMvc
    private MockMvc mvc;

    @Autowired // Inject repository
    private SeriesRepository repository;

    private static final String API_ROOT = "/api/series";

    /**
     * After each test, clear data
     */
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    /**
     * Check if we can get created Series when getting all Series
     * @throws Exception
     */
    @Test
    public void givenSeriess_whenGetSeriess_thenStatus200() throws Exception {
        String seriesName = randomAlphabetic(10);
        Series series = new Series();
        series.setName(seriesName);
        repository.save(series);
        mvc.perform(get("/api/series")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(seriesName)));
    }

    /**
     * Check if get all Series works
     * @throws Exception
     */
    @Test
    public void whenGetAllSeriess_thenOK() throws Exception {
        final Series seriesTest1 = createTestSeries();
        final Series seriesTest2 = createTestSeries();
        mvc.perform(get(API_ROOT).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("*", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is(seriesTest1.getName())))
                .andExpect(jsonPath("$[1].name", is(seriesTest2.getName())));
    }

    /**
     * Check if get a Series by name works
     * @throws Exception
     */
    @Test
    public void whenGetSeriessByName_thenOK() throws Exception {
        final Series seriesTest = createTestSeries();
        mvc.perform(get(API_ROOT + "/name/" + seriesTest.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))));
    }


    /**
     * Check if get a Series by id works
     * @throws Exception
     */
    @Test
    public void whenGetCreatedSeriesById_thenOK() throws Exception {
        final Series seriesTest = createTestSeries();
        mvc.perform(get(API_ROOT + "/" + seriesTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.name", is(seriesTest.getName())));
    }

    /**
     * Check that get not existing Series return 404 code
     * @throws Exception
     */
    @Test
    public void whenGetNotExistSeriesById_thenNotFound() throws Exception {
        mvc.perform(get(API_ROOT + "/" + randomNumeric(4)))
                .andExpect(status().isNotFound());
    }

    /**
     * Check that when creating Series return CREATED code
     * @throws Exception
     */
    // POST
    @Test
    public void whenCreateNewSeries_thenCreated() throws Exception {
        Series seriesToCreate = new Series();
        seriesToCreate.setName(randomAlphabetic(10));
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seriesToCreate)))
                .andExpect(status().isCreated());
    }

    /**
     * Check that when creating invalid Series return 400 code
     * @throws Exception
     */
    @Test
    public void whenInvalidSeries_thenError() throws Exception {
        Series seriesToCreate = new Series();
        seriesToCreate.setName(null);
        mvc.perform(post(API_ROOT).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seriesToCreate)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Check if updating Series works
     * @throws Exception
     */
    @Test
    public void whenUpdateCreatedSeries_thenUpdated() throws Exception {
        final Series seriesTest = createTestSeries();
        seriesTest.setName(randomAlphabetic(10));
        mvc.perform(put(API_ROOT + "/" + seriesTest.getId()).contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(seriesTest)))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + seriesTest.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("*", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.name", is(seriesTest.getName())));
    }

    /**
     * Check if deleting Series works
     * @throws Exception
     */
    @Test
    public void whenDeleteCreatedSeries_thenOk() throws Exception {
        final Series seriesTest = createTestSeries();
        mvc.perform(delete(API_ROOT + "/" + seriesTest.getId()))
                .andExpect(status().isOk());
        mvc.perform(get(API_ROOT + "/" + seriesTest.getId()))
                .andExpect(status().isNotFound());
    }

    /**
     * Generate a Series with a random name
     * @return Series created
     */
    private Series createTestSeries() {
        Series series = new Series();
        series.setName(randomAlphabetic(20));
        repository.save(series);
        return repository.findByName(series.getName());
    }
}