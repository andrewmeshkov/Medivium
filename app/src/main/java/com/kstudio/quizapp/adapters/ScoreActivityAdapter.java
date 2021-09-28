package com.kstudio.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kstudio.quizapp.R;
import com.kstudio.quizapp.StaticsFragment;
import com.kstudio.quizapp.model.Statistics;

import java.util.List;

public class ScoreActivityAdapter extends RecyclerView.Adapter<ScoreActivityAdapter.ViewHolder>{

    StaticsFragment context;
    List<Statistics> stList;

    public ScoreActivityAdapter(StaticsFragment context, List<Statistics> TempList) {

        this.stList = TempList;

        this.context = context;
    }

    @Override
    public ScoreActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);

        ScoreActivityAdapter.ViewHolder viewHolder = new ScoreActivityAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreActivityAdapter.ViewHolder holder, int position) {

        Statistics st = stList.get(position);

        holder.txCrt.setText(String.valueOf(st.getCorrect()));
        holder.txWrg.setText(String.valueOf(st.getWrong()));
        holder.txSkp.setText(String.valueOf(st.getSkip()));
        holder.txScr.setText(String.valueOf(st.getScore())+" %");
        holder.txSub.setText(st.getCategory());
        holder.txDate.setText(st.getDate());
    }

    @Override
    public int getItemCount() {
        return stList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txCrt;
        public TextView txWrg;
        public TextView txSkp;
        public TextView txScr;
        public TextView txDate;
        public TextView txSub;

        public ViewHolder(View itemView) {
            super(itemView);

            txCrt = itemView.findViewById(R.id.txCrt);
            txWrg = itemView.findViewById(R.id.txWrg);
            txSkp = itemView.findViewById(R.id.txSkp);
            txScr = itemView.findViewById(R.id.txScr);
            txDate = itemView.findViewById(R.id.txDate);
            txSub = itemView.findViewById(R.id.txSub);
        }
    }


}