package sorting.algorithms;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import static android.widget.Toast.*;

import android.annotation.TargetApi;
/**
 * Activity này xử lí layout activity_welcome
 * Hiển thị màn hình trong vòng 5s,
 *  sau đó chuyển sang activity ChoosingScreen
 *  
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Welcome extends ActionBarActivity {
	private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		if (getIntent().getBooleanExtra("EXIT", false)) {
			finish();
		}
	}
	/*
	 *Dừng màn hình 5s
	*Chuyển đến activity ChoosingScreen 
	*/
	public void start(){
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					sleep(5000);
					Intent intent = new Intent(getApplicationContext(), ChoosingScreen.class);
					startActivity(intent);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}    
	@Override
	public void onResume() {
		super.onResume();
		start();
	}
}
