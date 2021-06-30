package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTempIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeeService employeeService;

    @Test
    void testListEmployees() {
        employeeService.deleteAll();

        EmployeeDto employeeDto =
                template.postForObject("/api/employee",new CreateEmployeeCommand("John Doe"),EmployeeDto.class);

        assertEquals("John Doe",employeeDto.getName());

        template.postForObject("/api/employee",new CreateEmployeeCommand("Jane Doe"),EmployeeDto.class);

        List<EmployeeDto> emp = template.exchange("/api/employee",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {}
        ).getBody();

        assertThat(emp)
                .extracting(EmployeeDto::getName)
                .containsExactly("John Doe","Jane Doe");
    }
}
