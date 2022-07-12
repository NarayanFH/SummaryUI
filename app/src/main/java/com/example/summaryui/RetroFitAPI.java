package com.example.summaryui;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroFitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("users")
    //on below line we are creating a method to post our data.
    Call<DataModal> createPost(@Body DataModal dataModal);

    @GET()
    Call<GetModel> getId(@Field("user_id") GetModel user_id);

    @POST("userflow/getsummary/")
    Call<GetModel> getPost(@Body GetModel getModel);
}
