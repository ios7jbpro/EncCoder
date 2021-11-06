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
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.cardview.widget.CardView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends AppCompatActivity {
	
	private String your_version = "";
	private String package_name = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear4;
	private TextView textview3;
	private LinearLayout linear11;
	private ImageView imageview3;
	private ScrollView vscroll1;
	private LinearLayout linear9;
	private CardView cardview1;
	private CardView cardview2;
	private CardView cardview3;
	private LinearLayout linear2;
	private TextView textview4;
	private EditText edittext1;
	private Button button1;
	private LinearLayout linear7;
	private LinearLayout linear3;
	private CheckBox checkbox1;
	private TextView textview1;
	private ImageView imageview1;
	private LinearLayout linear5;
	private TextView textview5;
	private EditText edittext2;
	private Button button2;
	private LinearLayout linear6;
	private TextView textview2;
	private ImageView imageview2;
	private LinearLayout linear10;
	private TextView textview6;
	
	private SharedPreferences txt;
	private Intent openbsheet = new Intent();
	private RequestNetwork rq;
	private RequestNetwork.RequestListener _rq_request_listener;
	private Intent upt = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview3 = (TextView) findViewById(R.id.textview3);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		cardview1 = (CardView) findViewById(R.id.cardview1);
		cardview2 = (CardView) findViewById(R.id.cardview2);
		cardview3 = (CardView) findViewById(R.id.cardview3);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview4 = (TextView) findViewById(R.id.textview4);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		button1 = (Button) findViewById(R.id.button1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview5 = (TextView) findViewById(R.id.textview5);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		button2 = (Button) findViewById(R.id.button2);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		textview6 = (TextView) findViewById(R.id.textview6);
		txt = getSharedPreferences("txt", Activity.MODE_PRIVATE);
		rq = new RequestNetwork(this);
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				openbsheet.setClass(getApplicationContext(), SheetbActivity.class);
				startActivity(openbsheet);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_encrypt();
				textview1.setText(txt.getString("txt", ""));
				txt.edit().putString("txt", "").commit();
				linear3.setVisibility(View.VISIBLE);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (checkbox1.isChecked()) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString().concat("\n--------\n`This text has been encoded using EncCoder by ios7jbpro. Use EncCoder to decode it.`")));
				}
				else {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString()));
				}
				SketchwareUtil.showMessage(getApplicationContext(), "Copied");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_decrypt();
				textview2.setText(txt.getString("dxt", ""));
				txt.edit().putString("dxt", "").commit();
				linear6.setVisibility(View.VISIBLE);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview2.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Copied");
			}
		});
		
		_rq_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				listmap = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				if (Double.parseDouble(listmap.get((int)0).get("version").toString()) > Double.parseDouble(your_version)) {
					upt.setClass(getApplicationContext(), UpdatesheetbActivity.class);
					startActivity(upt);
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), "Checking update failed:No internet");
			}
		};
	}
	
	private void initializeLogic() {
		package_name = "com.ios7jbpro.enccoder";
		try {
			android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
			your_version = pinfo.versionName; }
		catch (Exception e){ showMessage(e.toString()); }
		rq.startRequestNetwork(RequestNetworkController.GET, "https://root.apurixz.com/file/user_upload/EncCoder/update.json", "A", _rq_request_listener);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =MainActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
		}
		linear3.setVisibility(View.GONE);
		linear6.setVisibility(View.GONE);
		txt.edit().putString("txt", "").commit();
		txt.edit().putString("dxt", "").commit();
		cardview1.setCardBackgroundColor(0xFF2196F3);
		cardview2.setCardBackgroundColor(0xFF2196F3);
		cardview3.setCardBackgroundColor(0xFFF44336);
		_rippleRoundStroke(button1, "#0D47A1", "#FFFFFF", 360, 0, "#000000");
		_rippleRoundStroke(button2, "#0D47A1", "#FFFFFF", 360, 0, "#000000");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _encrypt () {
		txt.edit().putString("txt", edittext1.getText().toString()).commit();
		_encCharacters();
		txt.edit().putString("txt", txt.getString("txt", "").replace("q", "1")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("Q", "~")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("w", "2")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("W", "`")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("e", "3")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("E", "|")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("r", "4")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("R", "•")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("t", "5")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("T", "√")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("y", "6")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("Y", "π")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("u", "7")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("U", "÷")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("i", "8")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("I", "×")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("o", "9")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("O", "¶")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("p", "0")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("P", "∆")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("a", "/")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("A", "£")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("s", "#")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("S", "¢")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("d", "$")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("D", "€")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("f", "_")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("F", "¥")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("g", "&")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("G", "^")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("h", "-")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("H", "°")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("j", "+")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("J", "=")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("k", "(")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("K", "{")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("l", ")")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("L", "}")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("z", "*")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("Z", "%")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("x", "\"")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("X", "©")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("c", "'")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("C", "®")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("v", ":")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("V", "™")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("b", ";")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("B", "✓")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("n", "!")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("N", "[")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("m", "?")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("M", "]")).commit();
	}
	
	
	public void _decrypt () {
		txt.edit().putString("dxt", edittext2.getText().toString()).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("1", "q")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("~", "Q")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("2", "w")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("`", "W")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("3", "e")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("|", "E")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("4", "r")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("•", "R")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("5", "t")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("√", "T")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("6", "y")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("π", "Y")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("7", "u")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("÷", "U")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("8", "i")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("×", "I")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("9", "o")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("¶", "O")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("0", "p")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("∆", "P")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("/", "a")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("£", "A")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("#", "s")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("¢", "S")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("$", "d")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("€", "D")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("_", "f")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("¥", "F")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("&", "g")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("^", "G")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("-", "h")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("°", "H")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("+", "j")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("=", "J")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("(", "k")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("{", "K")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace(")", "l")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("}", "L")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("*", "z")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("%", "Z")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("\"", "x")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("©", "X")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("'", "c")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("®", "C")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace(":", "v")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("™", "V")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace(";", "b")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("✓", "B")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("!", "n")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("[", "N")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("?", "m")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("]", "M")).commit();
		_decCharacters();
	}
	
	
	public void _encCharacters () {
		txt.edit().putString("txt", txt.getString("txt", "").replace(".", ",")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("1", "ㄅ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("2", "ㄉ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("3", "ˇ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("4", "ˋ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("5", "ㄓ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("6", "ˊ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("7", "·")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("8", "ㄚ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("9", "ㄞ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("0", "ㄢ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("'", "ㄆ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace(">", "ㄊ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("<", "ᝦ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace(":", "ᝣ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("(", "ᝤ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace(")", "ᝥ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("?", "ᝧ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("!", "ᝩ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace("(", "ㄥ")).commit();
		txt.edit().putString("txt", txt.getString("txt", "").replace(")", "ㄡ")).commit();
	}
	
	
	public void _decCharacters () {
		txt.edit().putString("dxt", txt.getString("dxt", "").replace(",", ".")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄅ", "1")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄉ", "2")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ˇ", "3")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ˋ", "4")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄓ", "5")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ˊ", "6")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("·", "7")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄚ", "8")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄞ", "9")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄢ", "0")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄆ", "'")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄊ", ">")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝦ", "<")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝣ", ":")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝤ", "(")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝥ", ")")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝧ", "?")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ᝩ", "!")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄥ", "(")).commit();
		txt.edit().putString("dxt", txt.getString("dxt", "").replace("ㄡ", ")")).commit();
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