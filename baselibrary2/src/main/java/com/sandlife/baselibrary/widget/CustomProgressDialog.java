package com.sandlife.baselibrary.widget;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.sandlife.baselibrary.R;

public class CustomProgressDialog extends Dialog {

	private String msg;
	
	public CustomProgressDialog(Context context) {
		super(context, R.style.new_circle_progress);
	}
	
	public CustomProgressDialog(Context context,String msg) {
		super(context, R.style.new_circle_progress);
		this.msg=msg;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 指定布局
		this.setContentView(R.layout.layout_progressbar);
		TextView tv=(TextView)findViewById(R.id.emptyView);
		if(msg!=null){
			tv.setText(msg);
		}
	}

}