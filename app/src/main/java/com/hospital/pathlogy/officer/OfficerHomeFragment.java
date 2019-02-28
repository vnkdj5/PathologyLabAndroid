package com.hospital.pathlogy.officer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hospital.pathlogy.ContactUsFragment;
import com.hospital.pathlogy.R;
import com.hospital.pathlogy.report.ReportListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficerHomeFragment extends Fragment {
    private Button btnClinic,btnReports,btnContact;


    public OfficerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_officer_home, container, false);
        btnReports=v.findViewById(R.id.buttonReportsOfficer);
        btnClinic=v.findViewById(R.id.buttonClinicOfficer);
        btnContact=v.findViewById(R.id.buttonContactUsOfficer);


        btnReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ReportListFragment();
                //fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("reportsFrag");
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        btnClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ReportListFragment();
                //fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("clinicfrag");
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new ContactUsFragment();
                //fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("contactFrag");
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });


        return v;
    }

}
