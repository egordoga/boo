package ua.booking;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.booking.entity.Booking;
import ua.booking.model.BookingForm;
import ua.booking.model.UserForm;

import static com.jayway.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void allBookingsTest() throws Exception {
        String uri = "/booking/all_bookings";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Booking[] bookings = super.mapFromJson(content, Booking[].class);
        assertTrue(bookings.length > 0);
    }

    @Test
    public void costBookingTest() {
        when().get("booking/cost?id=9")
                .then().statusCode(200)
                .and()
                .assertThat()
                .body("totalCost", equalTo(37.0f));
    }

    @Test
    public void addUserTest() throws Exception {
        String uri = "/user/add";
        UserForm userForm = new UserForm("Bird", "6666");

        String inputJson = super.mapToJson(userForm);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, inputJson);
    }

    @Test
    public void addBookingTest() throws Exception {

        String uri = "/user/add";
        BookingForm bookingForm = new BookingForm("Dog", "12", "2018-11-20", "2018-11-25", null);

        String inputJson = super.mapToJson(bookingForm);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);

    }

    @Test
    public void bookingsByUserTest() throws JSONException {
        Response response = get("/user/user_booking?user=Dog");
        int status = response.getStatusCode();
        assertEquals(200, status);
        JSONArray jsonResponse = new JSONArray(response.asString());
        String id = jsonResponse.getJSONObject(0).getString("id");
        Assert.assertEquals(id, "2");
    }

    @Test
    public void sendFreeRoomTest() {
        when().get("/room/free?start=30-11-18&end=20-12-18")
                .then()
                .statusCode(200)
                .and()
                .body("id", hasItems(3, 5));
    }

    @Test
    public void sendByCategoryTest() {
        when().get("/room/category?name=luxury")
                .then()
                .statusCode(200)
                .and()
                .body("id", hasItem(5));
    }
}

