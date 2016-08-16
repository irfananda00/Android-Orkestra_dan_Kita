package orkestrakita.com.orkestradankita;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    Drawable dr;
    Bitmap bitmap;
    Drawable d;
    RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bg = (RelativeLayout) findViewById(R.id.bg);
        initAsset();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MenuActivity.class));
                finish();
            }
        }, 3000);
    }

    private void initAsset() {
        dr = getResources().getDrawable(R.drawable.bg_splash);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 1000, true));
        bg.setBackground(d);
    }
}
