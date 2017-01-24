package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wangyuhang on 2017/1/23.
 */

public class AIDLService extends Service {
    private static final String TAG = "myLog";

    IGreetService.Stub stub = new IGreetService.Stub() {
        @Override
        public String sayHello(String someone) throws RemoteException {
            return "Hello, " + someone;
        }

        @Override
        public String greet(Person person) throws RemoteException {
            String greeting = "Hello, " + person.getName();
            switch (person.getSex()) {
                case 0:
                    greeting = greeting + ", you're handsome.";
                    break;
                case 1:
                    greeting = greeting + ", you're beautiful.";
                    break;
            }
            return greeting;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() called");
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind() called");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() called");
        super.onDestroy();
    }
}
