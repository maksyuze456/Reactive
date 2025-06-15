package org.training.reactive;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.training.reactive.controller.FireController;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.model.Status;
import org.training.reactive.service.FireService;

import java.util.Collections;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.training.reactive.model.Status.ACTIVE;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(FireController.class)
public class FireControllerIntTest {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    FireService fireService;


    @Test
    void testIfListOfActiveFiresRetrievedCorrect() throws Exception{
        Mockito.when(fireService.getAllActiveFires(Status.ACTIVE)).thenReturn(List.of(
                new FireResponseDTO(1, 30.20,  35.5, Status.ACTIVE, Collections.emptyList()),
                new FireResponseDTO(2, 15.24,  16.5, Status.ACTIVE, Collections.emptyList()),
                new FireResponseDTO(3, 11.52,  12.5, Status.ACTIVE, Collections.emptyList())
        ));
        RequestBuilder request = MockMvcRequestBuilders.get("/fires?status=ACTIVE");
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String json = result.getResponse().getContentAsString();
        System.out.println(json);
        List<FireResponseDTO> retrievedList = mapper.readValue(json, new TypeReference<List<FireResponseDTO>>() {
        });

        assertEquals(3, retrievedList.size());
        assertEquals(1, retrievedList.get(0).id());
        assertEquals(15.24, retrievedList.get(1).latitude());
        assertEquals(11.52, retrievedList.get(2).latitude());

    }

    @Test
    void testIfControllerHandlesJsonCorrect() throws Exception {
        FireRequestDTO fireRequestDTO = new FireRequestDTO(20.55, 14.98, Status.ACTIVE);

        Mockito.when(fireService.addFire(fireRequestDTO)).thenReturn(new FireResponseDTO(1L, fireRequestDTO.latitude(), fireRequestDTO.longtitude(), fireRequestDTO.status(), Collections.emptyList()));

        String json = mapper.writeValueAsString(fireRequestDTO);

        RequestBuilder request = MockMvcRequestBuilders.post("/fires")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println(jsonResponse);

        FireResponseDTO fireResponseDTO = mapper.readValue(jsonResponse, FireResponseDTO.class);

        assertEquals(20.55, fireResponseDTO.latitude());
        assertEquals(14.98, fireResponseDTO.longtitude());

    }

    @Test
    void testInvalidJson() throws Exception {

        // for invalid JSON to be ignored - spring.jackson.deserialization.fail-on-unknown-properties=true

        String invalidJson = """
                {
                    "latutude": 20.55,
                    "longtitude": 15.28,
                    "status": "ACTIVE"
                }
                """;
        RequestBuilder request = MockMvcRequestBuilders.post("/fires")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson);
        MvcResult response = mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(response.getResponse().getContentAsString());
        assertEquals(400, response.getResponse().getStatus());
    }

}
