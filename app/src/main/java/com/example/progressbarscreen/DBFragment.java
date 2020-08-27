package com.example.progressbarscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChartView;

public class DBFragment extends Fragment {


    public DBFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_d_b, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.anychart);
        anyChartView.setChart(GraphDataModel.setuppiechart("db"));
        return view;
    }
}