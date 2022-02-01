package with.dee2.happybirthday;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(new SplashHandler(),3000);


    }
    private class SplashHandler implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}