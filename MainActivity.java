package com.bmob.lostfound;

import com.bmob.BeanFile.Bean;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;


import cn.bmob.v3.listener.InsertListener;




public class MainActivity extends Activity implements OnClickListener {

	private EditText et_name, et_pass;
	private Button btn_login;
	private TextView tv_register;
	private Bean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		
		Bmob.initialize(this, "a2cb79744c5352b5cdacec0ff370c6d3");
		  
	    BmobInstallation.getCurrentInstallation(this).save();
	    
	    BmobPush.startWork(this, "a2cb79744c5352b5cdacec0ff370c6d3");
		initView();
	}

	private void initView() {
		bean = new Bean();
		et_name = (EditText) findViewById(R.id.et_name);
		et_pass = (EditText) findViewById(R.id.et_pass);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		
		tv_register = (TextView) findViewById(R.id.tv_register);
		tv_register.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_register:
			startActivity(new Intent(MainActivity.this, RegisterActivity.class));
			break;
		case R.id.btn_login:
			bean.setUsername(et_name.getText().toString());
			bean.setPassword(et_pass.getText().toString());
			bean.login(this, new InsertListener() {

				@Override
				public void onSuccess() {
					Intent intent = new Intent(MainActivity.this,MainActivity2.class);
	        		startActivity(intent);
					
						Toast.makeText(MainActivity.this, "登录成功",
								Toast.LENGTH_LONG).show();
					
				}

				

				@Override
				public void onFailure(String arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this, "登陆失败",
							Toast.LENGTH_LONG).show();
				}
			});
			break;
		 
		}
	}
}

