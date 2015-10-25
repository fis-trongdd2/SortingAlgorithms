package sorting.algorithms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Activity này xử lí layout activity_choosing_screen
 * Cho phép chọn 1 trong 3 thuật toán sắp xếp : Quick sort, Merge sort, Bubble sort
 *  sau đó chuyển sang activity SortAlgorithmScreen
 *  
 */
public class ChoosingScreen extends ActionBarActivity {
	Button bt1, bt2, bt3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choosing_screen);
		if (getIntent().getBooleanExtra("BACK", false)) {
			finish();
		}
		bt1 = (Button) findViewById(R.id.bubblesort);
		bt2 = (Button) findViewById(R.id.quicksort);
		bt3 = (Button) findViewById(R.id.mergesort);

		//truyền giá trị 1 đến activity tiếp theo, khi nhấn button bubble sort
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChoosingScreen.this, SortAlgorithmScreen.class);
				intent .putExtra("sort_algorithm", 1);
				startActivity(intent);
			}
		});

        //truyền giá trị 2 đến activity tiếp theo, khi nhấn button quick sort
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChoosingScreen.this, SortAlgorithmScreen.class);
				intent .putExtra("sort_algorithm", 2);
				startActivity(intent);
			}
		});
        
      //truyền giá trị 2 đến activity tiếp theo, khi nhấn button merge sort
		bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChoosingScreen.this, SortAlgorithmScreen.class);
				intent .putExtra("sort_algorithm", 3);
				startActivity(intent);
			}
		});
        
        
	}
	/*
	 *Hàm xử lí nếu người dùng ấn nút back trên thiết bị
	 *Đưa ra message hỏi xem có đồng ý thoát ứng dụng hay không 
	 */
	private void Exit(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// hiển thị tiêu đề
		builder.setTitle("Exit !!!");
		// chọn icon hiển thị
		builder.setIcon(R.drawable.smallicon);
		// cho click ra ngoài để thoát không.
		builder.setCancelable(true);
		// Chọn nội dung hiển thị
		builder.setMessage("Do you want to exit the application");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(getApplicationContext(), Welcome.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);
		} });
		builder.setNegativeButton("No", null);
		// hiển thị alertDialog
		builder.show();
}
@Override
public void onBackPressed() {
    Exit();
}
}
