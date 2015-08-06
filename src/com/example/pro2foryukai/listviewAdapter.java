package com.example.pro2foryukai;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listviewAdapter extends BaseAdapter{

	public ArrayList<HashMap<String,String>> list;  
    Activity activity; 
    
    
    public listviewAdapter(Activity activity, ArrayList<HashMap<String,String>> list) {  
        super();  
        this.activity = activity;  
        this.list = list;  
    }  
    
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自动生成的方法存根
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自动生成的方法存根
		return 0;
	}

	 private class ViewHolder {  
         TextView txtFirst;  
         TextView txtSecond;  
         TextView txtThird;  
    }  

	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO 自动生成的方法存根
		ViewHolder holder;  
        LayoutInflater inflater =  activity.getLayoutInflater();  

        if (arg1 == null)  
        {  
            arg1 = inflater.inflate(R.layout.listview_row, null);  
            holder = new ViewHolder();  
            holder.txtFirst = (TextView) arg1.findViewById(R.id.FirstText);  
            holder.txtSecond = (TextView) arg1.findViewById(R.id.SecondText);  
            holder.txtThird = (TextView) arg1.findViewById(R.id.ThirdText);  
            arg1.setTag(holder);  
        }  
        else  
        {  
            holder = (ViewHolder) arg1.getTag();  
        }  

        HashMap<String, String> map = list.get(arg0);  
        holder.txtFirst.setText(map.get("Time"));  
        holder.txtSecond.setText(map.get("Stop"));  
        holder.txtThird.setText(map.get("Num"));  

    return arg1;  
	}

}
