package com.hospital.pathlogy.adapters;

import android.view.View;

public interface RecyclerViewItemClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

