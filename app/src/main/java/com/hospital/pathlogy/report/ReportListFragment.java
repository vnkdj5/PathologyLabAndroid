package com.hospital.pathlogy.report;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hospital.pathlogy.Config;
import com.hospital.pathlogy.R;
import com.hospital.pathlogy.adapters.CustomRVItemTouchListener;
import com.hospital.pathlogy.adapters.RecyclerViewItemClickListener;
import com.hospital.pathlogy.adapters.ReportListAdapter;
import com.hospital.pathlogy.models.ReportListDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.v7.widget.DividerItemDecoration.HORIZONTAL;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


public class ReportListFragment extends Fragment {

    //Creating a List of reportListDetails
    private List<ReportListDetails> reportListDetails;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private String username;

    public ReportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_report_list, container, false);

        recyclerView = v.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        //Initializing our superheroes list
        reportListDetails = new ArrayList<>();

        //Calling method to get data
        getData();

        return v;
    }

    //This method will get data from the web api
    private void getData() {
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET,"https://api.myjson.com/bins/ji6qs" /*Config.SERVER_URL+"/getReports"/*/,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Dismissing progress dialog
                        loading.dismiss();
                        JSONArray arrayResponse = null;
                        try {
                            arrayResponse = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //calling method to parse json array
                        parseData(arrayResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                SharedPreferences sp = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                username = sp.getString(Config.USERNAME_PREF, null);
                params.put("username", username);


                //returning parameter
                return params;
            }
        };

        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            ReportListDetails reportListItem = new ReportListDetails();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                reportListItem.setTestName(json.getString(Config.TAG_TESTNAME));
                reportListItem.setDoctorName(json.getString(Config.TAG_DOCTORNAME));
                reportListItem.setLabCode(json.getString(Config.TAG_LABCODE));
                reportListItem.setAmount(json.getString(Config.TAG_AMOUNT));
                reportListItem.setSubmitDate(json.getString(Config.TAG_SUBMITDATE));
                reportListItem.setReportStatus(json.getString(Config.TAG_REPORTSTATUS));
                reportListItem.setReportId(json.getString(Config.TAG_REPORTID));
                /*   ArrayList<String> powers = new ArrayList<String>();

                JSONArray jsonArray = json.getJSONArray(Config.TAG_POWERS);

                for(int j = 0; j<jsonArray.length(); j++){
                    powers.add(((String) jsonArray.get(j))+"\n");
                }
                reportListDetails.setPowers(powers);
*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.reportListDetails.add(reportListItem);
        }

        //Finally initializing our adapter
        adapter = new ReportListAdapter(reportListDetails, getActivity());

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getActivity(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //     Toast.makeText(getActivity(),"CLICK "+position,Toast.LENGTH_SHORT).show();
                final ReportListDetails com = reportListDetails.get(position);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment = ReportDetailFragment.newInstance(com);
                ft.addToBackStack("reportDetails");
                ft.replace(R.id.content_frame, fragment);
                ft.commit();

            }

            @Override
            public void onLongClick(View view, int position) {

            }


        }));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Reports");
    }
}
