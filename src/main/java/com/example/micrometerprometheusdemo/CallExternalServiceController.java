package com.example.micrometerprometheusdemo;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CallExternalServiceController {


    private final MeterRegistry meterRegistry;
    private final RestTemplateBuilder restTemplateBuilder;

    public CallExternalServiceController(FruitService fruitService, MeterRegistry meterRegistry, RestTemplateBuilder restTemplateBuilder) {
        this.meterRegistry = meterRegistry;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping("/callsomeservice")
    public ResponseEntity callService() {
        ResponseEntity<GetEmployeeResponse> forEntity = restTemplateBuilder.build().getForEntity("http://dummy.restapiexample.com/api/v1/employees", GetEmployeeResponse.class);
        log.info(forEntity.getStatusCode().toString());
            return ResponseEntity.ok().build();
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GetEmployeeResponse {

    private List<Employee> employeeList;
    private String status;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Employee {
    private String profileImage;

    private String employeeName;

    private String employeeSalary;

    private String id;

    private String employeeAge;
}

