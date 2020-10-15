package com.example.stackexchange.api;

import com.example.stackexchange.model.StackResponse;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface StackService {

    @GET("2.2/questions?order=desc&sort=activity&site=stackoverflow")
    Single<StackResponse> getQuestionsList();

}
