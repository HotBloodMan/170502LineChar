package ting.example.linecharview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	private LineChar2 mSimpleLineChart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSimpleLineChart = (LineChar2) findViewById(R.id.simpleLineChart);
		String[] xItem = {"1","2","3","4","5","6","7"};
		String[] yItem = {"10k","20k","30k","40k","50k","60","70","80"};
		if(mSimpleLineChart == null)
		Log.e("wing","null!!!!");
		mSimpleLineChart.setXItem(xItem);
		mSimpleLineChart.setYItem(yItem);
		HashMap<Integer,Integer> pointMap = new HashMap();
		for(int i = 0;i<xItem.length;i++){
			pointMap.put(i, (int) (Math.random()*5));
		}
		mSimpleLineChart.setData(pointMap);
	}



}
