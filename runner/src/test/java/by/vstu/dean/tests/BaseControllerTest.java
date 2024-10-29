package by.vstu.dean.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class BaseControllerTest {
    
    protected MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
    }
    
    protected abstract Object getController();
    
    protected void performGet(String url, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().is(expectedStatus.value()));
    }
    
    protected void performPost(String url, Object requestBody, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestBody)))
                .andExpect(status().is(expectedStatus.value()));
    }
    
    protected void performPut(String url, Object requestBody, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestBody)))
                .andExpect(status().is(expectedStatus.value()));
    }
    
    protected void performDelete(String url, HttpStatus expectedStatus) throws Exception {
        mockMvc.perform(delete(url))
                .andExpect(status().is(expectedStatus.value()));
    }
    
    protected String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}