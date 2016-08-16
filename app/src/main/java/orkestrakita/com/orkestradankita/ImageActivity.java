package orkestrakita.com.orkestradankita;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

/**
 * Created by irfananda on 29/07/16.
 */
public class ImageActivity extends AppCompatActivity {

    private ImageView back, image, title;
    private LinearLayout bg;
    Drawable dr;
    Bitmap bitmap;
    Drawable d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String tag = getIntent().getStringExtra("TAG");

        bg = (LinearLayout) findViewById(R.id.bg);
        back = (ImageView) findViewById(R.id.back);
        image = (ImageView) findViewById(R.id.image);
        title = (ImageView) findViewById(R.id.title);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dr = getResources().getDrawable(R.drawable.bg_seatpln_chord);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 1000, true));
        bg.setBackground(d);
        dr = getResources().getDrawable(R.drawable.home2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        back.setImageDrawable(d);

        if (tag.equals(getString(R.string.piano_chord))) {
            initAssetPiano();
        }else if (tag.equals(getString(R.string.major_scales))){
            initAssetMajor();
        }
    }

    private void initAssetMajor() {
        image.setImageDrawable(getResources().getDrawable(R.drawable.img_majorscal));
        title.setImageDrawable(getResources().getDrawable(R.drawable.title_majorscale));
    }

    private void initAssetPiano() {
        image.setImageDrawable(getResources().getDrawable(R.drawable.img_chord));
        title.setImageDrawable(getResources().getDrawable(R.drawable.title_pianochord));
    }
}
