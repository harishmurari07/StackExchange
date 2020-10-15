package com.example.stackexchange.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackexchange.R;
import com.example.stackexchange.adapter.QuestionsAdapter;
import com.example.stackexchange.model.Item;
import com.example.stackexchange.model.StackResponse;
import com.example.stackexchange.repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class QuestionsActivity extends AppCompatActivity {

    private QuestionsRepository repository;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_screen);
        recyclerView = findViewById(R.id.recycler_view);
        repository = new QuestionsRepository();
        getRecentQuestions();
    }

    //Displays loading dialog
    private void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    //hides dialog if showing
    private void dismissLoadingDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    //call to api to get list of questions
    private void getRecentQuestions() {
        showLoadingDialog();
        if (repository != null) {
            repository.getRecentQuestions()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<StackResponse>() {
                        @Override
                        public void onSuccess(@NonNull StackResponse stackResponse) {
                            displayList(stackResponse);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            dismissLoadingDialog();
                            Log.d("Failure", "print");
                        }
                    });
        }
    }

    //sort and send to recyclerview to dispay on the UI
    private void displayList(StackResponse stackResponse) {
        dismissLoadingDialog();
        List<Item> responseList = stackResponse.items;
        List<String> questionsList = new ArrayList<>();

        for (Item item : responseList) {
            if (item.getAnswer_count() > 1 || item.getOwner().getAccept_rate() >= 1) {
                questionsList.add(item.getTitle());
            }
        }
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionsList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation()));
        recyclerView.setAdapter(questionsAdapter);
    }

}