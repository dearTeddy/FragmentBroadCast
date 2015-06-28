/**
 * 
 */
package com.example.broadcastfragmenttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Wuxiaohui
 *
 */
public final class GasFragment extends Fragment{
	private static final String TAG = GasFragment.class.getSimpleName();
	private TextView gasName;  
	private TextView gasadderss;  
	
	interface SendBroad2Fragment{
		void sendBroadcast();
	}
	
	private SendBroad2Fragment sendBroad2Fragment;
	
	public SendBroad2Fragment getSendBroad2Fragment() {
		return sendBroad2Fragment;
	}

	public void setSendBroad2Fragment(SendBroad2Fragment sendBroad2Fragment) {
		this.sendBroad2Fragment = sendBroad2Fragment;
	}
	private ReceiveBroadCast receiveBroadCast;  
	public  GasFragment (){
		ReceiveBroadCast receiveBroadCast =
				new ReceiveBroadCast(); 
	}  

	@Override  
	public void onAttach(Activity activity) {  
		Log.e(TAG, "onAttach");

		/** 注册广播 */  
		receiveBroadCast = new ReceiveBroadCast();  
		IntentFilter filter = new IntentFilter();  
		filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播  
		activity.registerReceiver(receiveBroadCast, filter); 
		sendBroad2Fragment.sendBroadcast();
		super.onAttach(activity); 
	}  
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {  
		View view=inflater.inflate(R.layout.fragment_gas, null);  
		Log.e(TAG, "onCreateView");
		gasName = (TextView) view.findViewById(R.id.frag_tv_gasname);  
		gasadderss = (TextView) view.findViewById(R.id.fraggas_tv_adderss);  

		return view;   

	}   

	protected class ReceiveBroadCast extends BroadcastReceiver  
	{  
		@Override  
		public void onReceive(Context context, Intent intent)  
		{  
			Log.e(TAG, "onReceive");
			//得到广播中得到的数据，并显示出来  
			String gasname = intent.getExtras().getString("gasName");  
			String address = intent.getExtras().getString("address");  

			gasadderss.setText("地址：\n  "+address);  
			gasName.setText(gasname);  
			
		}  
	}  
	/**
	 *注销广播
	 * */  
	@Override  
	public void onDestroyView() {  
		getActivity().unregisterReceiver(receiveBroadCast);
		Log.e(TAG, "onDestroyView");
		super.onDestroyView();  
	}  
}
