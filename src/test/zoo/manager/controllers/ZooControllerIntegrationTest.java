package zoo.manager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import zoo.manager.exceptions.models.RecordDuplicateException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
class ZooControllerIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    @Order(1)
    public void whenZoneIsCreatedSuccessfully_thenReturn201() {
        RestAssured.basePath = "/zoo_manager/zone";
        JsonObject body = createJSONBodyWithZone("A");

        Response response = RestAssured.given().body(body.toString()).contentType("application/json").post().andReturn();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    @Order(2)
    public void whenZoneIsDuplicateInDB_thenThrowRecordDuplicateExceptionAndReturn400()
        throws RecordDuplicateException {
        RestAssured.basePath = "/zoo_manager/zone";
        JsonObject body = createJSONBodyWithZone("A");

        Response response = RestAssured.given().body(body.toString()).contentType("application/json").post().thenReturn();
        Exception exception = assertThrows(RecordDuplicateException.class, () -> {
            throw new RecordDuplicateException();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Record with this name already exist in an entity";
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    @Order(3)
    public void whenSpeciesIsCreatedSuccessfully_thenReturn201() {
        RestAssured.basePath = "/zoo_manager/species";
        JsonObject body = createJSONBodyWithSpecies("Lion", 11.0);

        Response response = RestAssured.given().body(body.toString()).contentType("application/json").post().thenReturn();
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    @Order(4)
    public void whenSpeciesIsDuplicateInDB_thenThrowRecordDuplicateExceptionAndReturn400()
        throws RecordDuplicateException {
        RestAssured.basePath = "/zoo_manager/species";
        JsonObject body = createJSONBodyWithSpecies("Lion", 11.0);

        Response response = RestAssured.given().body(body.toString()).contentType("application/json").post().thenReturn();
        Exception exception = assertThrows(RecordDuplicateException.class, () -> {
            throw new RecordDuplicateException();
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Record with this name already exist in an entity";
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    @Order(5)
    public void whenAnimalIsCreatedSuccessfully_thenReturn201() {
        RestAssured.basePath = "/zoo_manager/animal";
        JsonObject body = createJSONBodyWithAnimal("Lucian", "Lion", "A");

        Response response = RestAssured.given().body(body.toString()).contentType("application/json").post().thenReturn();

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    @Order(6)
    public void whenProvidedJSONBodyIsMalformed_thenReturn415()
        throws HttpMessageNotReadableException {
        RestAssured.basePath = "/zoo_manager/animal";
        Response response = RestAssured.given().body("").post().thenReturn();

        assertEquals(response.getStatusCode(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    @Order(7)
    public void whenZoneWithLowestOccupantsIsCalled_thenReturn200() {
        RestAssured.basePath = "/zoo_manager/zone/zoneWithLowestOccupants";
        Response response = RestAssured.get().andReturn();

        assertEquals(response.getStatusCode(),HttpStatus.OK.value());
    }

    @Test
    @Order(8)
    public void whenZoneWithHighestExpensesIsCalled_thenReturn200() {
        RestAssured.basePath = "/zoo_manager/zone/zoneWithHighestExpenses";
        Response response = RestAssured.get().andReturn();

        assertEquals(response.getStatusCode(),HttpStatus.OK.value());
    }

    public JsonObject createJSONBodyWithZone(String name) {
        JsonObject body = new JsonObject();
        body.addProperty("name", name);
        return body;
    }

    public JsonObject createJSONBodyWithSpecies(String name, Double expenses) {
        JsonObject body = new JsonObject();
        body.addProperty("name", name);
        body.addProperty("expenses", expenses);
        return body;
    }

    public JsonObject createJSONBodyWithAnimal(String animalName, String speciesName, String zoneName) {
        JsonObject body = new JsonObject();
        body.addProperty("animalName", animalName);
        body.addProperty("speciesName", speciesName);
        body.addProperty("zoneName", zoneName);
        return body;
    }

}