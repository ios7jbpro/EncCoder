package com.ios7jbpro.enccoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class UpdatesheetbActivity extends AppCompatActivity {
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private TextView textview2;
	private TextView textview3;
	private TextView textview1;
	private TextView textview4;
	
	private RequestNetwork rq;
	private RequestNetwork.RequestListener _rq_request_listener;
	private Intent upt = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.updatesheetb);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview4 = (TextView) findViewById(R.id.textview4);
		rq = new RequestNetwork(this);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				upt.setAction(Intent.ACTION_VIEW);
				upt.setData(Uri.parse(listmap.get((int)0).get("link").toString()));
				startActivity(upt);
				finish();
			}
		});
		
		_rq_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				listmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				textview2.setText(listmap.get((int)0).get("desc").toString());
				textview4.setText(listmap.get((int)0).get("version").toString());
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =UpdatesheetbActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.TRANSPARENT);
		}
		rq.startRequestNetwork(RequestNetworkController.GET, "https://root.apurixz.com/file/user_upload/EncCoder/update.json", "A", _rq_request_listener);
		_rippleRoundStroke(linear1, "#FFFFFF", "#FFFFFF", 40, 0, "#000000");
		_rippleRoundStroke(linear2, "#FFFFFF", "#FFFFFF", 360, 0, "#000000");
		_rippleRoundStroke(textview3, "#FFFFFF", "#2196F3", 360, 0, "#000000");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _convertToBottomSheet () {
	}
	private androidx.coordinatorlayout.widget.CoordinatorLayout mCoordinatorLayout;
	@Override
	public void finish(){
		com.google.android.material.bottomsheet.BottomSheetBehavior.from(mCoordinatorLayout.getChildAt(0)).setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
	}
	private void superFinish(){
		super.finish();
	}
	 @Override
	public void setContentView(int layId){
			if(mCoordinatorLayout == null){
					overridePendingTransition(0,0);
					mCoordinatorLayout = new androidx.coordinatorlayout.widget.CoordinatorLayout(this);
					makeActivityTransparent();
			mCoordinatorLayout.setBackgroundColor(0x80000000);
					mCoordinatorLayout.setOnClickListener(new View.OnClickListener(){
							@Override
							public void onClick (View v){
										finish();
							}
					});
			}
			mCoordinatorLayout.removeAllViews();
			androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			final com.google.android.material.bottomsheet.BottomSheetBehavior behavior = new com.google.android.material.bottomsheet.BottomSheetBehavior();
			params.setBehavior(behavior);
			behavior.setBottomSheetCallback(new com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback(){
					@Override
					public void onSlide(View bottomSheet, float slideOffset){
							
					}
					@Override
					public void onStateChanged(View bottomSheet, int newState){
							if(newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED){
									superFinish();
					overridePendingTransition(0,0);
							}
					}
			});
			View inflated = getLayoutInflater().inflate(layId,null);	
			mCoordinatorLayout.addView(inflated,params);
			
			if(mCoordinatorLayout.getParent()!= null)((ViewGroup)mCoordinatorLayout.getParent()).removeView(mCoordinatorLayout);
			setContentView(mCoordinatorLayout);
		inflated.post(new Runnable(){
			@Override
			            public void run() {
				behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED);
			}});
		
	}
	
	private void makeActivityTransparent(){
		getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
			try {
					java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 
					getActivityOptions.setAccessible(true);
					Object options = getActivityOptions.invoke(this);
					Class<?>[] classes = Activity.class.getDeclaredClasses();
					Class<?> translucentConversionListenerClazz = null;
					for (Class clazz : classes) { 
							if (clazz.getSimpleName().contains("TranslucentConversionListener")) { 
									translucentConversionListenerClazz = clazz;
							} 
					} 
					java.lang.reflect.Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class); 
					convertToTranslucent.setAccessible(true); 
					convertToTranslucent.invoke(this, null, options); 
			} catch (Throwable t) {
			}
	}
	
	{
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}