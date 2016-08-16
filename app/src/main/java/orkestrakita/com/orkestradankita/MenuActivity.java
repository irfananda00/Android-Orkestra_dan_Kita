package orkestrakita.com.orkestradankita;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by irfananda on 29/07/16.
 */
public class MenuActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 5;
    private ImageView qrcode, seating_plan, piano_chord, major_scales;
    private TableLayout bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bg = (TableLayout) findViewById(R.id.bg);
        qrcode = (ImageView) findViewById(R.id.qrcode);
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //permission camera
                if (Build.VERSION.SDK_INT >= 23) {
                    handlePermissionsCamera();
                }else{
                    startActivity(new Intent(MenuActivity.this,ScannerActivity.class));
                }
            }
        });

        seating_plan = (ImageView) findViewById(R.id.seating_plan);
        seating_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open seating plan
                startActivity(new Intent(MenuActivity.this,SeatingPlanActivity.class));
            }
        });

        piano_chord = (ImageView) findViewById(R.id.piano_chord);
        piano_chord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open piano chord
                Intent intent = new Intent(MenuActivity.this,ImageActivity.class);
                intent.putExtra("TAG",getString(R.string.piano_chord));
                startActivity(intent);
            }
        });

        major_scales = (ImageView) findViewById(R.id.major_scales);
        major_scales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open major scales
                Intent intent = new Intent(MenuActivity.this,ImageActivity.class);
                intent.putExtra("TAG",getString(R.string.major_scales));
                startActivity(intent);
            }
        });

        initAsset();
    }

    private void initAsset() {
        Drawable dr;
        Bitmap bitmap;
        Drawable d;

        dr = getResources().getDrawable(R.drawable.bg_menu_dan_alatmusik);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 400, 800, true));
        bg.setBackground(d);
//        dr = getResources().getDrawable(R.drawable.btn_qrcode);
//        bitmap = ((BitmapDrawable) dr).getBitmap();
//        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 300, 600, true));
//        qrcode.setImageDrawable(d);
//        dr = getResources().getDrawable(R.drawable.btn_seatingplan);
//        bitmap = ((BitmapDrawable) dr).getBitmap();
//        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 300, 600, true));
//        seating_plan.setImageDrawable(d);
//        dr = getResources().getDrawable(R.drawable.btn_pianochord);
//        bitmap = ((BitmapDrawable) dr).getBitmap();
//        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 300, 600, true));
//        piano_chord.setImageDrawable(d);
//        dr = getResources().getDrawable(R.drawable.btn_majorscales);
//        bitmap = ((BitmapDrawable) dr).getBitmap();
//        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 300, 600, true));
//        major_scales.setImageDrawable(d);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void handlePermissionsCamera() {
            Log.v("infoirfan", "handlePermissionsCamera");
            int hasWriteCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
            if (hasWriteCameraPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }else{
                startActivity(new Intent(MenuActivity.this,ScannerActivity.class));
            }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS :
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startActivity(new Intent(MenuActivity.this,ScannerActivity.class));
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
