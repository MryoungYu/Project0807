package com.example.pro2foryukai;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class secondPage extends Activity{
	private TextView tv_driver,tv_line,tv_starttime,tv_arrivetime;
	private static final String[] str = {"张江17A线","张江17B线"};
	private ArrayList<HashMap<String,String>> list; 
	private Spinner sp;
	private ListView lv;
	private ArrayAdapter<String> adapter;
	private final static String TEL_NUM = "123456";
	
	public Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x11) {
				//UI更新
				Bundle bundle = new Bundle();
				bundle.clear();
				bundle = msg.getData();
				tv_driver.setText(bundle.getString("driver"));
				tv_starttime.setText(bundle.getString("start_time"));
				tv_arrivetime.setText(bundle.getString("arrive_time"));
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondpage);
		sp = (Spinner)findViewById(R.id.spinner1);
		lv = (ListView)findViewById(R.id.listView1);
		tv_driver = (TextView)findViewById(R.id.tv_driver);
		tv_line = (TextView)findViewById(R.id.tv_line);
		tv_starttime = (TextView)findViewById(R.id.tv_starttime);
		tv_arrivetime = (TextView)findViewById(R.id.tv_arrivetime);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,str);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		sp.setVisibility(View.VISIBLE);
		
		list = new ArrayList<HashMap<String,String>>();  
         
	        HashMap<String,String> temp = new HashMap<String,String>();  
	            temp.put("Time","时间");  
	            temp.put("Stop", "站点");  
	            temp.put("Num", "人数");  
	        list.add(temp);  
	          
	        HashMap<String,String> temp1 = new HashMap<String,String>();  
	        	temp1.put("Time","17:40");  
	        	temp1.put("Stop", "张江渠电港二期");  
	        	temp1.put("Num", "6");  
	        list.add(temp1);  
	          
	        HashMap<String,String> temp2 = new HashMap<String,String>();  
	        	temp2.put("Time","17:45");  
	        	temp2.put("Stop", "张江渠电港一期");  
	        	temp2.put("Num", "5");  
	        list.add(temp2);  
	          
	        HashMap<String,String> temp3 = new HashMap<String,String>();  
	        	temp3.put("Time","18:20");  
	        	temp3.put("Stop", "复兴路");  
	        	temp3.put("Num", "2");  
	        list.add(temp3);  
	        
	        listviewAdapter lvadapter = new listviewAdapter(this, list);  
	        lv.setAdapter(lvadapter);  
	        
	        new MyThread().start();
	          
	}
	
	class MyThread extends Thread{
		
		public void run(){
			try {
				Log.i("TAG", "hello");
			HttpClient client = new DefaultHttpClient();
			StringBuilder builder = new StringBuilder();
			HttpGet myget = new HttpGet(
					"http://7.test000001.sinaapp.com/getMessage.php?num=" + TEL_NUM);			// 服务器的域名
			HttpResponse response = client.execute(myget);
			HttpEntity entity = response.getEntity();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(entity.getContent()));
			for (String s = reader.readLine(); s != null; s = reader
					.readLine()) {
				builder.append(s);
			}
			JSONObject jsonObject = new JSONObject(builder.toString());		// 获取数据
			String driver = jsonObject.getString("driver");
			String start_time = jsonObject.getString("start_time");
			String arrive_time = jsonObject.getString("arrive_time");
			Message msg = Message.obtain();
			msg.what = 0x11;
			Bundle b = new Bundle();
			b.clear();
			b.putString("driver", driver);
			b.putString("start_time", start_time);
			b.putString("arrive_time", arrive_time);
			msg.setData(b);
			myHandler.sendMessage(msg);										// 通知主线程
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

	}

}


