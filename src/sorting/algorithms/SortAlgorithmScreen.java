package sorting.algorithms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Activity này xử lí layout activity_sort_algorithm_screen
 * Cho phép nhap vao 1 so trong khoang tu 1 den 10
 *  sau đó chuyển sang activity SortArrayScreen 
 *  
 */

public class SortAlgorithmScreen extends ActionBarActivity{
	TextView tv3;
	EditText ed3;
	Button bt3;
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_algorithm_screen);
        tv3 = (TextView) findViewById(R.id.textView3);

        ed3 = (EditText) findViewById(R.id.editText3);
        bt3 = (Button) findViewById(R.id.button3);
        Intent get = getIntent();
        index = get.getIntExtra("sort_algorithm", 0);

        //lấy giá trị index từ layout trước, đặt tiêu đề tùy theo thuật toán đã lựa chọn
        if (index == 1) {
        	tv3.setText("The Bubble sort is choosen");
        }
        if (index == 2) {
        	tv3.setText("The Quick sort is choosen");
        }
        if (index == 3) {
        	tv3.setText("The Merge sort is choosen");
        }

        //bắt sự kiện ấn button OK
        bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int  number = Integer.parseInt(ed3.getText().toString());
					// TODO Auto-generated method stub
				if (number <= 10 && number > 0) {

					//nếu số nhập vào thỏa mãn, chuyển sang màn hình SortArrayScreen, truyền đi giá trị nhập vào					
					Intent intent = new Intent(SortAlgorithmScreen.this, SortArrayScreen.class);
					intent.putExtra("sort_algorithm", index);
					intent.putExtra("number", number);
					startActivity(intent);
				} else {
					//nếu không thỏa mãn, hiển thị thông báo
						Toast.makeText(getApplicationContext(),
								"Please enter number from 1 to 10", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
	}
}
