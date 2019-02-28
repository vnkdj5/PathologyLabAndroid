package com.hospital.pathlogy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hospital.pathlogy.report.ReportListFragment;


public class HomeFragment extends Fragment {

    private Button btnClinic,btnReports,btnAbout,btnContact;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        btnReports=v.findViewById(R.id.buttonReports);
        btnClinic=v.findViewById(R.id.buttonClinic);
        btnAbout=v.findViewById(R.id.buttonAboutUs);
        btnContact=v.findViewById(R.id.buttonContactUs);

        btnReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for passing data between fragments
                //Bundle bundle = new Bundle();
                //bundle.putInt("spinner_id", 0);
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

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AboutUsFragment();
                //fragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack("AboutusFrag");
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }
    }

