package com.scraper.main.controllers;

import com.scraper.main.Application;
import com.scraper.main.pojo.ErrorMessageEnumConstant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CustomErrorControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void noParamErrorShouldReturnErrorMessage() throws Exception {
		this.mockMvc.perform(get("/error"))
				.andDo(print())
                .andExpect(jsonPath("$.error").value(ErrorMessageEnumConstant.ERROR.toString()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorMessageEnumConstant.ERROR.getMessage()))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
    }

    @Test
    public void invalidParamCoreShouldReturn4xxErrorAndCoreErrorMessage() throws Exception {
        this.mockMvc.perform(get("/api/core=111"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value(ErrorMessageEnumConstant.CORE.toString()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorMessageEnumConstant.CORE.getMessage()))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
    }

    @Test
    public void emptyParamTermShouldReturn4xxErrorAndCoreErrorMessage() throws Exception {
        this.mockMvc.perform(get("/api/class/term="))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value(ErrorMessageEnumConstant.TERM.toString()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorMessageEnumConstant.TERM.getMessage()))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
    }

    @Test
    public void invalidParamTermShouldReturn4xxErrorAndCoreErrorMessage() throws Exception {
        this.mockMvc.perform(get("/api/class/term=111"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value(ErrorMessageEnumConstant.TERM.toString()))
                .andExpect(jsonPath("$.errorMessage").value(ErrorMessageEnumConstant.TERM.getMessage()))
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"));
    }


}