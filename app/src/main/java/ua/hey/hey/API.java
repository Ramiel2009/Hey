package ua.hey.hey;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Maxim on 12/9/15.
 */
public interface API {
    @GET("/hey-rest-hibernate/users/login?")
    Call<StackOverflowQuestions> load (@Query("login")String tags);
}
