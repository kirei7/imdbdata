package com.vlad.imdbdata.web.controller;

import com.vlad.imdbdata.basis.config.MediaType;
import com.vlad.imdbdata.basis.service.MediaInfoService;
import com.vlad.imdbdata.web.WebTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebTestConfig.class })
@WebAppConfiguration
public class ImportMediaInfoControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ImportMediaInfoController controller;
    @Autowired
    private MediaInfoService service;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void importMediaMethodShouldBeCalled() throws Exception {
        String title = "titanic";
        String type = "movie";
        mockMvc.perform(get("/api")
                .param("title", title)
                .param("mediaType", type)
        )
                .andExpect(status().is2xxSuccessful());
        verify(service).importMedia(title, null, MediaType.valueOf(type.toUpperCase()));
    }

    @Test
    public void wrongParametersShouldCause4xxErrors() throws Exception {
        String title = "titanic";
        String type = "movie";
        mockMvc.perform(get("/api")
                .param("title", title)
        ).andExpect(status().is4xxClientError());
        mockMvc.perform(get("/api")
                .param("type", type)
        ).andExpect(status().is4xxClientError());
        mockMvc.perform(get("/api")
                .param("type", type)
                .param("year", "characters")
        ).andExpect(status().is4xxClientError());

    }

}
