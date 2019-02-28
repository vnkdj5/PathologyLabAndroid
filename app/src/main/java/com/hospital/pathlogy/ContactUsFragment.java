package com.hospital.pathlogy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Element adsElement = new Element();
        adsElement.setTitle("Call Us");
        adsElement.setValue("1234567890");

        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("Description  about pathology lab.Description  about pathology lab.Description  about pathology lab.Description  about pathology lab.")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("01vaibhavkumbhar@gmail.com")
                .addWebsite("http://pathlogylab.com/")
                .addFacebook("vnkdj5")
                .addTwitter("vnkdj5")
                .addPlayStore("com.hospital.pathlogy")
                .addInstagram("_vaibhav_k")
                .addGitHub("vnkdj5")
                .addItem(getCopyRightsElement())
                .create();
       // View v =inflater.inflate(R.layout.fragment_contact_us, container, false);
        return aboutPage;
    }
    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
