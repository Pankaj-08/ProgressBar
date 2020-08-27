package com.example.progressbarscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChartView;

public class DesignFragment extends Fragment {

    public DesignFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_design, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.anychart);
        anyChartView.setChart(GraphDataModel.setuppiechart("design"));
        Log.d("Replyy"," - "+anyChartView.getClass());
        Log.d("Replyyy From ", "Value is: " + GraphDataModel.setuppiechart("code"));
        return view;
    }
}