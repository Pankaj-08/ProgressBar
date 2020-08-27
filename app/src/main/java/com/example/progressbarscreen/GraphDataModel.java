package com.example.progressbarscreen;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Align;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GraphDataModel extends Fragment {



    private static List<DataEntry> codedataEntries = new ArrayList<>();
    private static List<DataEntry> dbdataEntries = new ArrayList<>();
    private static List<DataEntry> designdataEntries = new ArrayList<>();
  static Double codetotal=0.0;
  static   Double designtotal=0.0;
  static   Double dbtotal = 0.0;


    public GraphDataModel() {
    }

    public GraphDataModel(String name) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Progress");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", "Value is: " + dataSnapshot);
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Iterable<DataSnapshot> s;
                    if(ds.getKey().equals("Code"))
                    {
                        Log.d("TAGDv", "Value is: " + ds.getChildren());
                        s =  ds.getChildren();
                        Log.d("TAGDvs", "Value is: " + s);
                        setCodedataEntries(s);
                    }
                    else if(ds.getKey().equals("DB"))
                    {
                        Log.d("TAGDv", "Value is: " + ds.getChildren());
                        s =  ds.getChildren();
                        setDbdataEntries(s);
                        Log.d("TAGDvs", "Value is: " + s);

                    }
                    else if(ds.getKey().equals("Design"))
                    {
                        Log.d("Design TAg", "Value is: " + ds.getChildren());
                        s =  ds.getChildren();
                        setDesigndataEntries(s);
                        Log.d("Design valueee ", "Value is: " + s);
                    }
                }
                getAlltotal();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    public static Pie setuppiechart(String name)
    {
        Pie pie = AnyChart.pie();
        switch(name)
        {
            case "code":
                pie.data(codedataEntries);
                pie.title("Dev Thread -> Code Progress");
                break;
            case "design":
                pie.data(designdataEntries);
                pie.title("Dev Thread -> Design Progress");
                break;
            case "db":
                pie.data(dbdataEntries);
                pie.title("Dev Thread -> DB Progress");
                break;
        }
        pie.labels().position("outside");
        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Tables")
                .padding(0d, 0d, 10d, 0d);
        pie.legend()
                .position("bottom")
                .itemsLayout(LegendLayout.VERTICAL)
                .align(Align.CENTER);
        return pie;
    }




    public void setCodedataEntries(Iterable<DataSnapshot> dataSnapshot)
    {
        for (DataSnapshot ds: dataSnapshot ) {
            Log.d("TAGDvs In Method", "Value is: " + ds);
            String s = ds.getKey();
            Log.d("TAGDvsInMethod", "Value is: " + s);
            int val =  Integer.parseInt(ds.getValue().toString());
            Log.d("TAGDvsInMethod", "" + val);
            this.codetotal+=val;
            Log.d("TValue Code", "" + codetotal);
            codedataEntries.add(new ValueDataEntry(s,val));
        }
    }


    public void setDbdataEntries(Iterable<DataSnapshot> dataSnapshot)
    {
        for (DataSnapshot ds: dataSnapshot ) {
            Log.d("TAGDvs In Method", "Value is: " + ds);
            String s = ds.getKey();
            Log.d("TAGDvsInMethod", "Value is: " + s);
            int val =  Integer.parseInt(ds.getValue().toString());
            Log.d("TAGDvsInMethod", "" + val);
            this.dbtotal+=val;
            Log.d("TValue DB", "" + dbtotal);
            dbdataEntries.add(new ValueDataEntry(s,val));
        }
    }


    public void setDesigndataEntries(Iterable<DataSnapshot> dataSnapshot)
    {
        for (DataSnapshot ds: dataSnapshot ) {
            Log.d("TAGDvs In Method", "Value is: " + ds);
            String s = ds.getKey();
            Log.d("TAGDvsInMethod", "Value is: " + s);
            int val =  Integer.parseInt(ds.getValue().toString());
            Log.d("TAGDvsInMethod", "" + val);
            this.designtotal+=val;
            Log.d("TValue Design", "" + designtotal);
            designdataEntries.add(new ValueDataEntry(s,val));
        }
    }

    public static int getCodetotal() {
            int total = (int) Math.round(codetotal / (codedataEntries.size()));
        return total;
    }


    public static int getDesigntotal() {
        int total = (int) Math.round(designtotal / (designdataEntries.size()));
        return total;
    }


    public  static int getDbtotal() {
        int total = (int) Math.round(dbtotal / (dbdataEntries.size()));
        return total;
    }


    public static int getAlltotal() {
        int total = (int) (getCodetotal()+getDbtotal()+getDesigntotal())/3;
        Log.d("Totalll All", "" + total);
        return total;
    }



    public static Cartesian waterfall()
    {
        Cartesian cartesian = AnyChart.column();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("ALL", getAlltotal()));
        data.add(new ValueDataEntry("Code", getCodetotal()));
        data.add(new ValueDataEntry("Design", getDesigntotal()));
        data.add(new ValueDataEntry("DB", getDbtotal()));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }%");

        cartesian.animation(true);
        cartesian.title("Average of ALL Progress");
        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }%");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Progress");

        return cartesian;
    }




}
