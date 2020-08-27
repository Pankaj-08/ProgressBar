package com.example.progressbarscreen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.anychart.AnyChartView;


public class AllFragment extends Fragment {
    AnyChartView anyChartView;
    GraphDataModel graphDataModel = new GraphDataModel();

    public AllFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        anyChartView.setChart(graphDataModel.waterfall());
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        anyChartView = view.findViewById(R.id.anychart);
        anyChartView.setChart(graphDataModel.waterfall());
        return view;

    }

}