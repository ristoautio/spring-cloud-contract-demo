package com.example.springcloudcontractdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(OutputCaptureExtension.class)
@WithLocalDateTime(datetime= "2023-10-01T12:00:00")
public class TestControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testEndpointReturnsSuccessMessage(CapturedOutput capturedOutput) {
        webTestClient
            .get()
            .uri("/test")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(String.class)
            .isEqualTo("Test successful!");

        assertThat(capturedOutput.getOut())
                .contains("Request received at /test endpoint");

        assertThat(capturedOutput.getOut())
                .contains("Time is: 2023-10-01T12:00:00Z");
    }
}