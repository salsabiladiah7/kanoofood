package com.mafaa.kanoofood.customer_ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mafaa.kanoofood.R;

public class OrderFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cust_fragment_order, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        return root;
    }
}