package api.test;

import api.endpoints.UserEndPoints;
import com.github.javafaker.*;
import api.payloads.UserPojo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker;
    UserPojo userPayload;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new UserPojo();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setUsername(faker.name().username());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }
    @Test(priority = 1)
    public void testPostUser(){
         Response response = UserEndPoints.createUser(userPayload);
         response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3)
    public void updateUserName(){
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertNotEquals(response.statusCode(), 404);

        //checking data after update the user
        Response response1 = UserEndPoints.readUser(this.userPayload.getUsername());
        response1.then().log().all();
        Assert.assertEquals(response1.getStatusCode(),200);


    }
    @Test (priority = 4)
    public void deleteUser(){
         Response response =UserEndPoints.deleteUser(userPayload.getUsername());
         response.then().log().all();
         Assert.assertNotEquals(response.statusCode(),400);
    }
}
