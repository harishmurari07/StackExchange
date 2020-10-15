package com.example.stackexchange.repository;

import com.example.stackexchange.api.RetroApi;
import com.example.stackexchange.api.StackService;
import com.example.stackexchange.model.StackResponse;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class QuestionsRepository {

    //Get Questions
    public Single<StackResponse> getRecentQuestions() {
        return RetroApi.getService(StackService.class).getQuestionsList();
    }

}
