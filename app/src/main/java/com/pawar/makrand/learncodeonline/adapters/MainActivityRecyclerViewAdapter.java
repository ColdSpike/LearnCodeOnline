package com.pawar.makrand.learncodeonline.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.pawar.makrand.learncodeonline.R;
import com.pawar.makrand.learncodeonline.retrofit.utils.DataStructureResponse;

import java.util.List;

public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter<MainActivityRecyclerViewAdapter.MainActivityRecyclerViewViewHolder> {

    private final List<DataStructureResponse.QuestionAnswer> questions;
    private final Context mainContext;

    public MainActivityRecyclerViewAdapter(Context context, List<DataStructureResponse.QuestionAnswer> questions){
        this.questions = questions;
        mainContext = context;
    }

    @NonNull
    @Override
    public MainActivityRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_recyclerview_single_card,parent,false);
        return new MainActivityRecyclerViewViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainActivityRecyclerViewViewHolder holder, int position) {
        holder.question.setText("Q"+(position+1)+" "+questions.get(position).question);
        holder.answer.setText(questions.get(position).Answer);

        final FlexboxLayout.LayoutParams p = (FlexboxLayout.LayoutParams)holder.question.getLayoutParams();
        Resources r = mainContext.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8,
                r.getDisplayMetrics()
        );
        p.setMargins(px,px,px,px);
        holder.question.setLayoutParams(p);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.answer.getVisibility() == View.GONE){
                    holder.answer.setVisibility(View.VISIBLE);
                }else{
                    holder.answer.setVisibility(View.GONE);
                    holder.question.setLayoutParams(p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class MainActivityRecyclerViewViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        TextView answer;
        CardView cv;
        public MainActivityRecyclerViewViewHolder(View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.mainActivityQuestion);
            answer = itemView.findViewById(R.id.mainActivityAnswer);
            cv = (CardView) itemView;
        }
    }
}
