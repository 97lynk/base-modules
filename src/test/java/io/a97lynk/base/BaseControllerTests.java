package io.a97lynk.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.a97lynk.base.contoller.CORSFilter;
import io.a97lynk.base.contoller.EmployeeController;
import io.a97lynk.base.exception.DataException;
import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.jpa.EmployeeEntity;
import io.a97lynk.base.jpa.EmployeeRepository;
import io.a97lynk.base.service.impl.BaseServiceImpl;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BaseControllerTests {

    private MockMvc mockMvc;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private BaseServiceImpl<EmployeeEntity, EmployeeDto, EmployeeRepository> baseService;

    private boolean init = false;

    @InjectMocks
    private EmployeeController employeeController;

    private List<EmployeeDto> dtos = new ArrayList<>();

    @BeforeEach
    public void init() {
        if (init) return;

        init = true;

        // setup mock
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .addFilters(new CORSFilter())
                .build();

        // init data
        EmployeeDto dto1 = new EmployeeDto();
        dto1.setId(1l);
        dto1.setName("Tuan Nguyen 1");

        EmployeeDto dto2 = new EmployeeDto();
        dto2.setId(2l);
        dto2.setName("Tuan Nguyen 2");

        dtos.add(dto1);
        dtos.add(dto2);

        Mockito.when(employeeService.searchAll()).thenReturn(dtos);
        Mockito.when(employeeService.searchAll(any(Pageable.class))).thenReturn(new PageImpl<>(dtos));
        Mockito.when(employeeService.insert(any(EmployeeDto.class))).then(invocationOnMock -> dto1);
        Mockito.when(employeeService.searchById(anyLong())).then(invocationOnMock -> {
            switch (invocationOnMock.getArgument(0, Long.class).intValue()) {
                case 1:
                    return dto1;
                case 2:
                    return dto2;
                default:
                    throw new DataException("Not found");
            }
        });

    }

    @Test
    public void insert() throws Exception {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Tuan Nguyen 1");
        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.name").value(dto.getName()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }


    @Test
    public void selectAll() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isArray())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void selectPage() throws Exception {
        mockMvc.perform(get("/employees/page?page={1}&size={2}", 0, 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.numberOfElements").value(2))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void searchById() throws Exception {
        mockMvc.perform(get("/employees/{1}", 1l))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void searchById_fails() throws Exception {
        mockMvc.perform(get("/employees/{1}", 3l))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$").exists())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    public void update() throws Exception {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("New Tuan Nguyen");

        mockMvc.perform(put("/employees/{1}", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    private String asJsonString(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
