package me.yokeyword.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.yokeyword.sample.spf.User_Spf;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvShow;
    private EditText mEtName, mEtMobile;
    private Button mBtnSave, mBtnRemoveName, mBtnRemoveAll, mBtnExistsName, mBtnGet;

    private User_Spf mUserSpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserSpf = User_Spf.create(this);
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                // 单数据提交
//                mUserSpf.name().put(mEtName.getText().toString());
                // 事务提交
                mUserSpf.edit()
                        .name()
                        .put(mEtName.getText().toString())
                        .mobile()
                        .put(mEtMobile.getText().toString())
                        .apply();
                break;
            case R.id.btn_remove_name:
                mUserSpf.name().remove();
                Toast.makeText(getApplicationContext(), "移除name成功!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exsits_name:
                Toast.makeText(getApplicationContext(), mUserSpf.name().exists() + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_remove_all:
                mUserSpf.edit().clear();
                Toast.makeText(getApplicationContext(), "全部移除成功!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_get:
                String name = mUserSpf.name().get();
                String mobile = mUserSpf.mobile().get();
                mTvShow.setText("name:" + name + "\n" + "mobile:" + mobile);
                break;
        }
    }

    private void initListener() {
        mBtnGet.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnRemoveName.setOnClickListener(this);
        mBtnExistsName.setOnClickListener(this);
        mBtnRemoveAll.setOnClickListener(this);
    }

    private void initView() {
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnRemoveName = (Button) findViewById(R.id.btn_remove_name);
        mBtnExistsName = (Button) findViewById(R.id.btn_exsits_name);
        mBtnRemoveAll = (Button) findViewById(R.id.btn_remove_all);
        mBtnGet = (Button) findViewById(R.id.btn_get);
    }
}
