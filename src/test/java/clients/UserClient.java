package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/";

    @Step("Создаем пользователя через API")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post("auth/register");
    }

    @Step("Удаляем пользователя через API")
    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .delete("auth/user");
    }

    @Step("Логинимся через API")
    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post("auth/login");
    }

    @Step("Получаем токен доступа из ответа")
    public String getAccessToken(Response response) {
        return response.then().extract().path("accessToken");
    }

}
