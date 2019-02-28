package com.hospital.pathlogy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hospital.pathlogy.R;
import com.hospital.pathlogy.models.ReportListDetails;
import com.hospital.pathlogy.models.ReportRow;

import java.util.List;

public class ReportDetailAdapter  extends RecyclerView.Adapter<ReportDetailAdapter.ViewHolder> {



    //List of reportListDetails
    List<ReportRow> reportRows;
    private Context context;

    public ReportDetailAdapter(List<ReportRow> reportDetails, Context context) {
        super();
        //Getting all the superheroes
        this.reportRows = reportDetails;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_table_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ReportRow row = this.reportRows.get(position);

        holder.textViewCol1.setText(row.getId());
        holder.textViewCol2.setText(row.getName());
        holder.textViewCol3.setText(row.getRange());
        holder.textViewCol4.setText(row.getValue());

    }

    @Override
    public int getItemCount() {
        return reportRows.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCol1;
        public TextView textViewCol2;
        public TextView textViewCol4;
        public TextView textViewCol3;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewCol1 = itemView.findViewById(R.id.tvCol1);
            textViewCol2 = itemView.findViewById(R.id.tvCol2);
            textViewCol3 = itemView.findViewById(R.id.tvCol3);
            textViewCol4 = itemView.findViewById(R.id.tvCol4);

        }
    }
}
