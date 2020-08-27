package com.example.progressbarscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChartView;

public class CodeFragment extends Fragment {

    public CodeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_code, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.anychart);
        anyChartView.setChart(GraphDataModel.setuppiechart("code"));
        return view;
    }
}