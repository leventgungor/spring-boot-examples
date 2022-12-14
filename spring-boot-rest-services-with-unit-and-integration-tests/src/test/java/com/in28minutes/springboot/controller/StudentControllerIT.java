package com.in28minutes.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.in28minutes.springboot.StudentServicesApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StudentServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveAllStudents() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students/getAll"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"id\":\"Student1\",\"name\":\"Ahmet Burhan\",\"department\":\"History\",\"gpa\":2.7},{\"id\":\"Student2\",\"name\":\"Selim Sek\",\"department\":\"History\",\"gpa\":2.48},{\"id\":\"Student3\",\"name\":\"Behzat Çınar\",\"department\":\"Law\",\"gpa\":1.9},{\"id\":\"Student4\",\"name\":\"Bahar Songül\",\"department\":\"Math\",\"gpa\":3.4}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students/Student1/courses/Course1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    public void deleteCourseFromStudentWhenCourseNotExists() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students/Student1/courses/delete/Course15"),
                HttpMethod.GET, entity, String.class);
        Assertions.assertFalse(Boolean.valueOf(response.getBody()));
    }

    @Test
    public void deleteCourseFromStudentWhenCourseExists() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/students/Student1/courses/delete/Course1"),
                HttpMethod.GET, entity, String.class);
        Assertions.assertTrue(Boolean.valueOf(response.getBody()));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
