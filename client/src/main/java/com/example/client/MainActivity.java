package com.example.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.aidldemo.IGreetService;
import com.example.aidldemo.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLog";

    @BindView(R.id.bindBtn)
    Button bindBtn;
    @BindView(R.id.greetBtn)
    Button greetBtn;
    @BindView(R.id.unbindBtn)
    Button unbindBtn;

    private IGreetService greetService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected() called");
            greetService = IGreetService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected() called");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bindBtn, R.id.greetBtn, R.id.unbindBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bindBtn:
                bind();
                break;
            case R.id.greetBtn:
                greet();
                break;
            case R.id.unbindBtn:
                unbind();
                break;
        }
    }

    private void bind() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.AIDLService");
        intent.setPackage("com.example.aidldemo");

        bindService(intent, connection, BIND_AUTO_CREATE);

        bindBtn.setEnabled(false);
        greetBtn.setEnabled(true);
        unbindBtn.setEnabled(true);
    }

    private void greet() {
        Person person = new Person();
        person.setName("Steven");
        person.setSex(0);

        try {
            if(greetService != null) {
                String retVal = greetService.greet(person);
                Toast.makeText(MainActivity.this, retVal, Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    private void unbind() {
        unbindService(connection);

        bindBtn.setEnabled(true);
        greetBtn.setEnabled(false);
        unbindBtn.setEnabled(false);
    }
}
