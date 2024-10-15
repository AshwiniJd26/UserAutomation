package api.endpoints;

import api.payloads.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

//Created for CURD
public class UserEndPoints {

    static ResourceBundle getUrl(){
       ResourceBundle myResources = ResourceBundle.getBundle("routes"); // load properties file
       return myResources;
    }

    public static Response createUser(UserPojo payload){

        String postURL = getUrl().getString("postURL");

        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postURL);
    return response ;

    }

    public static Response readUser(String username){
        String getURL = getUrl().getString("getURL");
        Response response= given()
                .pathParam("username",username)
                .when()
                .get(getURL);
        return response ;
    }

    public static Response updateUser(UserPojo payload, String username){
        String putURL = getUrl().getString("putURL");
        Response response= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when().put(putURL);
        return response ;
    }

    public static Response deleteUser(String username){
        String deleteURL = getUrl().getString("deleteURL");
        Response response= given()
                .pathParam("username",username)
                .when()
                .get(deleteURL);
        return response ;
    }

}
