package com.example.broadcastfragmenttest;

import com.example.broadcastfragmenttest.GasFragment.ReceiveBroadCast;
import com.example.broadcastfragmenttest.GasFragment.SendBroad2Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements SendBroad2Fragment{
	 private static final String TAG = MainActivity.class.getSimpleName(); 
	 private GasFragment gasFragment;       //加油站的fragment  
	 private ReceiveBroadCast broadCast;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e(TAG, "onCreate");
		gasFragment = new GasFragment();  
        FragmentManager fm = getSupportFragmentManager();  
        FragmentTransaction ft = fm.beginTransaction();  
        ft.replace(R.id.main_fl_fragment, gasFragment);// 将帧布局替换成Fragment  
        gasFragment.setSendBroad2Fragment(this);
        ft.commit();// 提交  
        
//		IntentFilter filter = new IntentFilter();  
//		filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播  
//		registerReceiver(broadCast, filter);  
        
        Log.e(TAG, "提交后");
        Intent intent = new Intent(); // Itent就是我们要发送的内容  
        intent.setAction("com.gasFragment"); // 设置你这个广播的action  
        intent.putExtra("gasName","核反应能量加油站");  
        intent.putExtra("address", "太平洋街11号");  
        sendBroadcast(intent); // 发送广播  
        Log.e(TAG, "发送广播");
        
        findViewById(R.id.tv).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Intent intent = new Intent(); // Itent就是我们要发送的内容  
		        intent.setAction("com.gasFragment"); // 设置你这个广播的action  
		        intent.putExtra("gasName","核反应能量加油站");  
		        intent.putExtra("address", "太平洋街11号");  
		        sendBroadcast(intent); // 发送广播  
			}
		});
        
        BroadRunnable runs=new BroadRunnable();  
        //获取线程对象  
        Thread tr=new Thread(runs);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//        tr.start();
 	}
	
	class BroadRunnable implements Runnable{

		@Override
		public void run() {
	        Intent intent = new Intent(); // Itent就是我们要发送的内容  
	        intent.setAction("com.gasFragment"); // 设置你这个广播的action  
	        intent.putExtra("gasName","核反应能量加油站");  
	        intent.putExtra("address", "太平洋街11号");  
	        sendBroadcast(intent); // 发送广播  
	        
	        Log.e(TAG, "Thread Broadcast");
		}
		
	}

	@Override
	public void sendBroadcast() {
        Intent intent = new Intent(); // Itent就是我们要发送的内容  
        intent.setAction("com.gasFragment"); // 设置你这个广播的action  
        intent.putExtra("gasName","核反应能量加油站");  
        intent.putExtra("address", "太平洋街11号");  
        sendBroadcast(intent); // 发送广播  
	}
	
}
