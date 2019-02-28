package com.hospital.pathlogy.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hospital.pathlogy.R;
import com.hospital.pathlogy.models.ReportListDetails;

import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    //List of reportListDetails
    List<ReportListDetails> reportListDetails;
    private Context context;


    public ReportListAdapter(List<ReportListDetails> reportListDetails, Context context) {
        super();
        //Getting all the superheroes
        this.reportListDetails = reportListDetails;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_detail_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ReportListDetails reportListDetails = this.reportListDetails.get(position);

        holder.textViewtestName.setText(reportListDetails.getTestName()+"("+reportListDetails.getReportId()+")");
        holder.textViewdoctorName.setText(String.valueOf(reportListDetails.getDoctorName()));
        holder.textViewLabCode.setText(reportListDetails.getLabCode());
        holder.textViewAmount.setText(reportListDetails.getAmount());
        holder.textViewReportStatus.setText(reportListDetails.getReportStatus());
        holder.textViewSubmitDate.setText(reportListDetails.getSubmitDAte());

    }

    @Override
    public int getItemCount() {
        return reportListDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewtestName;
        public TextView textViewdoctorName;
        public TextView textViewLabCode;
        public TextView textViewAmount;
        public TextView textViewSubmitDate;
        public TextView textViewReportStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewtestName = itemView.findViewById(R.id.testName);
            textViewdoctorName = itemView.findViewById(R.id.doctorName);
            textViewLabCode = itemView.findViewById(R.id.labCode);
            textViewAmount = itemView.findViewById(R.id.Amount);
            textViewSubmitDate = itemView.findViewById(R.id.submitDate);
            textViewReportStatus = itemView.findViewById(R.id.reportStatus);
        }
    }

}