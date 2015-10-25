package sorting.algorithms;

import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity này xử lí layout activity_sort_array_screen
 * Hiển thị 1 mảng random, có số phần tử = số nhập vào trong activity trước
 *  Button next chuyển sang activity ResultScreen 
 *  
 */

public class SortArrayScreen extends ActionBarActivity{
	TextView tv1, tv2, tv3;
	Button bt1;
	int array[];
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sort_array_screen);
		tv1 = (TextView) findViewById(R.id.textView4_1);
		tv2 = (TextView) findViewById(R.id.textView4_2);
		tv3 = (TextView) findViewById(R.id.textView4_3);
		bt1 = (Button) findViewById(R.id.button4);
		
		Intent intent = getIntent();
		index = intent.getIntExtra("sort_algorithm", 0);
		int n = intent.getIntExtra("number", 0);
		array = creatArray(n);
		
		//Tùy thuộc vào giá trị index, cài đặt các tiêu đề khác nhau với từng thuật toán
	    if (index == 1) {
        	tv1.setText("The Bubble sort is choosen");
        }
        if (index == 2) {
        	tv1.setText("The Quick sort is choosen");
        }
        if (index == 3) {
        	tv1.setText("The Merge sort is choosen");
        }
    	tv2.setText("Choosen array for sorting. There are "+n+" numbers.");
            
        String printarray = "";
        for (int i = 0; i < n; i++) {
        	printarray += array[i] + " ";
        }
        tv3.setText(printarray);
        
        //bắt sự kiện ấn button next,chuyển sang màn hình ResultScreen
        //truyền giá trị là mảng array và số index để xác định thuật toán
        bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SortArrayScreen.this,ResultScreen.class);
				Bundle sent_array = new Bundle();
				sent_array.putIntArray("value", array);
				i.putExtras(sent_array);
				i.putExtra("index", index);
				startActivity(i);

			}
		});
	}
	/*
	 * hàm tạo mảng ngẫu nhiên n phần tử
	 */
	public int [] creatArray(int n) {
		int a[] = new int[n];
		int i;
		for (i = 0; i < n; i++) {
			Random rd = new Random();
			a[i] = rd.nextInt(99);
		}
		return a;
	}
}
