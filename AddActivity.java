package com.bmob.lostfound;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.bmob.v3.listener.InsertListener;


import com.bmob.lostfound.bean.Lost;


public class AddActivity extends BaseActivity implements OnClickListener {

	EditText edit_title, edit_describe;
	Button btn_back, btn_true;

	TextView tv_add;
	String from = "";
	
	String old_title = "";
	String old_describe = "";
	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_add);
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		tv_add = (TextView) findViewById(R.id.tv_add);
		btn_back = (Button) findViewById(R.id.btn_back);
		btn_true = (Button) findViewById(R.id.btn_true);
		edit_describe = (EditText) findViewById(R.id.edit_describe);
		edit_title = (EditText) findViewById(R.id.edit_title);
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		btn_back.setOnClickListener(this);
		btn_true.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		from = getIntent().getStringExtra("from");
		old_title = getIntent().getStringExtra("title");
		old_describe = getIntent().getStringExtra("describe");
		
		edit_title.setText(old_title);
		edit_describe.setText(old_describe);
		tv_add.setText("日记本");
		
			
	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btn_true) {
			addByType();
		} else if (v == btn_back) {
			finish();
		}
	}
	String title = "";
	String describe = "";
	
	
	private void addByType(){
		title = edit_title.getText().toString();
		describe = edit_describe.getText().toString();
		
		if(TextUtils.isEmpty(title)){
			ShowToast("请填写标题");
			return;
		}
		if(TextUtils.isEmpty(describe)){
			ShowToast("请填写内容");
			return;
		}

		
			addLost();
		
	}

	private void addLost(){
		Lost lost = new Lost();
		lost.setDescribe(describe);
		lost.setTitle(title);
		lost.insertObject(this, new InsertListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				ShowToast("日记添加成功!");
				setResult(RESULT_OK);
				finish();
			}
			
			@Override
			public void onFailure(String arg0) {
				// TODO Auto-generated method stub
				ShowToast("添加失败:"+arg0);
			}
		});
	}
}
