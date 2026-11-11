package com.morningsync.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DailyReviewController.class)
public class DailyReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DailyReviewService dailyReviewService;

    @Test
    void shouldRejectTaskWithBlankTitle() throws Exception {
        mockMvc.perform(
                post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title": ""}
                                """)
        ).andExpect(status().isBadRequest());

        verifyNoInteractions(dailyReviewService);
    }

    @Test
    void shouldCreateTaskWithValidTitle() throws Exception {
        TaskResponse response =
                new TaskResponse(1L, "Invata Spring", false, null);

        when(dailyReviewService.createTask(
                new CreateTaskRequest("Invata Spring", null)
        )).thenReturn(response);

        mockMvc.perform(
                        post("/api/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {"title": "Invata Spring"}
                                """)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Invata Spring"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}