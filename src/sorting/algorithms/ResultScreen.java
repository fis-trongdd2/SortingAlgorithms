package sorting.algorithms;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity này xử lí layout activity_result_screen
 *Hiển thị từng bước sắp xếp và kết quả cuối cùng
 *  Button reset chuyển về activity ChoosingScreen,
 *  cho phép lựa chọn lại thuật toán. 
 *  Ý tưởng : 1 SpannableStringBuilder sẽ lần lượt thêm vào từng bước của thuật toán,
 *  cuối cùng thêm vào textView đặt trong layout
 */

public class ResultScreen extends ActionBarActivity{
	int array[];
	TextView tv1, tv2, tv3; 
	Button bt1;
	SpannableStringBuilder ssb;
	int stepQuick = 1;
	int stepMerge = 1;
	int stepBubble = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_screen);
		tv1 = (TextView) findViewById(R.id.textView5_1);
		tv2 = (TextView) findViewById(R.id.textView5_2);
		tv3 = (TextView) findViewById(R.id.textView5_3);
		bt1 = (Button) findViewById(R.id.button5);
		
		Bundle bundle = new Bundle();
		bundle = this.getIntent().getExtras();
		array = bundle.getIntArray("value");
		int index = bundle.getInt("index");
		//bubble sort
		if (index == 1) {
        	tv1.setText("The Bubble sort is choosen");
        	tv2.setText("Bubble sort result");
        	ssb = new SpannableStringBuilder();
			ssb.append(print("Initial array:\n", Color.BLACK));
			ssb.append(printArray(array) + "\n\n");
			bubbleSort(array, array.length, ssb, tv3);
			tv3.append(print("Result:\n", Color.BLACK));
			tv3.append(printArray(array));
		}

		//Quick sort
		if (index == 2) {
			tv1.setText("The Quick sort is choosen");
        	tv2.setText("Quick sort result");
    		ssb = new SpannableStringBuilder();
			ssb.append(print("Initial array:\n", Color.BLACK));
			ssb.append(printArray(array) + "\n\n");
			quickSort(array, 0, array.length - 1, ssb, tv3);
			tv3.append(print("Result:\n", Color.BLACK));
			tv3.append(printArray(array));
			}

		//merge sort
		if (index == 3) {
			tv1.setText("The Merge sort is choosen");
        	tv2.setText("Merge sort result");
        	ssb = new SpannableStringBuilder();
			ssb.append(print("Initial array:\n", Color.BLACK));
			ssb.append(printArray(array) + "\n\n");
			mergeSort(array, 0, array.length-1,ssb,tv3);
			tv3.append(print("Result:\n", Color.BLACK));
			tv3.append(printArray(array));
		        	
		}
		//bắt sự kiện ấn button reset
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),ChoosingScreen.class);
				startActivity(intent);
			}
		});
	}
	
	/*
	 * 2 phuong sau thực hiện thuật toán merge sort với mảng a[]
	 * trong đó, từng bước hòa nhập thành mảng cuối cùng được lưu vào SpannableStringBuilder str, 
	 * cuối cùng set cho TextView textview
	 */

	int b[];
	private void Merge(int a[], int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = 0;
		int n = right - left + 1;
		b = new int[n];
		while ((i < mid + 1) && (j < right + 1)) {
			if (a[i] < a[j]) {
				b[k] = a[i];
				k++;
				i++;
			}
			else {
				b[k] = a[j];
				k++;
				j++;
			}
		
		}
		while (i < mid + 1) {
			b[k] = a[i];
			k++;
			i++;
		}
		while (j < right + 1) {
			b[k] = a[j];
			k++;
			j++;
		}
		i = left;
		for (k = 0; k < n; k++) {
			a[i] = b[k];
			i++;
		}
	}
	
	public void mergeSort (int a[], int left,int right, SpannableStringBuilder str, TextView textview) {
		if (left < right ) {
			int mid = (left + right)/2;
			mergeSort(a, left, mid, str, textview);
			mergeSort(a, mid+1, right, str, textview);
			stepBubble++;
			str.append(printStyle("Step " + stepBubble + "\n", Typeface.BOLD));
			Merge(a, left, mid,right);
			ssb.append(printArray(b) + "\n\n");

			}
		textview.setText(str);
	}
	
	/*
	 * phuong thuc sau thực hiện thuật toán bubble sort với mảng a[]
	 * trong đó, từng bước duyet mang duoc luu vao SpannableStringBuilder str, 
	 * cuối cùng set cho TextView t
	 */
	public void bubbleSort (int a[], int l, SpannableStringBuilder rs, TextView t) {
		int i;
		int j;
		int temp;
		for (i = 0; i < l - 1; i++) {
			for (j = i  + 1; j < l; j++) {
				stepBubble++;
				rs.append(printStyle("Step " + stepBubble + "\n", Typeface.BOLD));
				if (a[i] > a[j]) {
					
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					ssb.append(printArray(a) + "\n\n");
					rs.append("\nswap " + String.valueOf(a[j])
						+ ", " + String.valueOf(a[i]) + "\n\n");
				}
				else {
					ssb.append(printArray(a) + "\n\n");
					rs.append("\ndo not swap\n\n");
				}
			}
		}
		t.setText(rs);
	}
	
	/*
	 * phuong thuc sau thực hiện thuật toán quick sort với mảng a[]
	 * trong đó, từng buoc duoc luu vao SpannableStringBuilder str, 
	 * cuối cùng set cho TextView textview
	 */
	public void quickSort(int a[], int left, int right, SpannableStringBuilder str, TextView textview) {
		if (left > right) return;
		int i = left;
		int j = right;
		int x = a[(left + right) / 2];
		while (i <= j) {
			while (a[i] < x) i++;
			while (a[j] > x) j--;
			if (i <= j) {
				str.append(printStyle("Step " + stepQuick + "\n", Typeface.BOLD));
				
				if (a[i] != a[j]) {
					
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					str.append(printArray(a) + "\n\n");
					str.append("\nswap " + String.valueOf(a[i])+ ", " + String.valueOf(a[j]) + "\n\n");
				}
				else {
					str.append(printArray(a) + "\n\n");
					str.append("\ndo not swap\n\n");
				}
				i++;
				j--;
			}			
		}
		textview.setText(str);
		stepQuick++;
		quickSort(a, left, j, str, textview);
		quickSort(a, i, right, str, textview);
	}
	
	//phuong thuc tra ve kieu String la chuoi luu mang theo dinh dang : [a[0] - a[1] - a[2] -.....- a[n]
	public String printArray(int [] arr) {	
		String tmp = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i != 0)
				tmp += (" - " + arr[i]);
			else
				tmp += arr[i];
		}
		return tmp + ")";
	}
	//2 phuong thuc tra ve chuoi co mau sac cac kieu
	public SpannableStringBuilder print(String s, int color) {
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		ssb.append(s);
		ssb.setSpan(new ForegroundColorSpan(color), 0, ssb.length(), 0);
		return ssb;
	}
	public SpannableStringBuilder printStyle(String s, int style) {
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		ssb.append(s);
		ssb.setSpan(new StyleSpan(style), 0, ssb.length(), 0);
		return ssb;
	}

}

