package com.example.stackexchange.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackexchange.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private List<String> responseList;

    public QuestionsAdapter(List<String> response) {
        responseList = response;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.setData(responseList.get(position));
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionText, acceptRate;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
        }

        public void setData(String response) {
            questionText.setText(response);
        }
    }

}
