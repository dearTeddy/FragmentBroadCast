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
	 private GasFragment gasFragment;       //����վ��fragment  
	 private ReceiveBroadCast broadCast;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e(TAG, "onCreate");
		gasFragment = new GasFragment();  
        FragmentManager fm = getSupportFragmentManager();  
        FragmentTransaction ft = fm.beginTransaction();  
        ft.replace(R.id.main_fl_fragment, gasFragment);// ��֡�����滻��Fragment  
        gasFragment.setSendBroad2Fragment(this);
        ft.commit();// �ύ  
        
//		IntentFilter filter = new IntentFilter();  
//		filter.addAction("com.gasFragment");    //ֻ�г�����ͬ��action�Ľ����߲��ܽ��մ˹㲥  
//		registerReceiver(broadCast, filter);  
        
        Log.e(TAG, "�ύ��");
        Intent intent = new Intent(); // Itent��������Ҫ���͵�����  
        intent.setAction("com.gasFragment"); // ����������㲥��action  
        intent.putExtra("gasName","�˷�Ӧ��������վ");  
        intent.putExtra("address", "̫ƽ���11��");  
        sendBroadcast(intent); // ���͹㲥  
        Log.e(TAG, "���͹㲥");
        
        findViewById(R.id.tv).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Intent intent = new Intent(); // Itent��������Ҫ���͵�����  
		        intent.setAction("com.gasFragment"); // ����������㲥��action  
		        intent.putExtra("gasName","�˷�Ӧ��������վ");  
		        intent.putExtra("address", "̫ƽ���11��");  
		        sendBroadcast(intent); // ���͹㲥  
			}
		});
        
        BroadRunnable runs=new BroadRunnable();  
        //��ȡ�̶߳���  
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
	        Intent intent = new Intent(); // Itent��������Ҫ���͵�����  
	        intent.setAction("com.gasFragment"); // ����������㲥��action  
	        intent.putExtra("gasName","�˷�Ӧ��������վ");  
	        intent.putExtra("address", "̫ƽ���11��");  
	        sendBroadcast(intent); // ���͹㲥  
	        
	        Log.e(TAG, "Thread Broadcast");
		}
		
	}

	@Override
	public void sendBroadcast() {
        Intent intent = new Intent(); // Itent��������Ҫ���͵�����  
        intent.setAction("com.gasFragment"); // ����������㲥��action  
        intent.putExtra("gasName","�˷�Ӧ��������վ");  
        intent.putExtra("address", "̫ƽ���11��");  
        sendBroadcast(intent); // ���͹㲥  
	}
	
}
