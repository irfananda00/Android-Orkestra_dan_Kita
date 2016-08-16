package orkestrakita.com.orkestradankita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import orkestrakita.com.orkestradankita.qrscanner.CustomScanner;

/**
 * Created by irfananda on 29/07/16.
 */
public class ScannerActivity extends AppCompatActivity implements CustomScanner.ResultHandler {

    private CustomScanner cameraView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        cameraView = (CustomScanner) findViewById(R.id.camera_view);
        cameraView.setResultHandler(this);
        cameraView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.i("infoirfan",result.getText());
        //check result
        checkResult(result.getText());
        finish();
    }

    private void checkResult(String resultText) {
        switch (resultText){
            case "http://q-r.to/baeeg5" :
                //bass drum
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_bass_drum);
                intent.putExtra("ALAT",R.drawable.item_bass_drum);
                intent.putExtra("TITLE",R.drawable.title_bassdrum);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefl" :
                //bassoon
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_bassoon);
                intent.putExtra("ALAT",R.drawable.item_bassoon);
                intent.putExtra("TITLE",R.drawable.title_basson);
                startActivity(intent);
                break;
            case "http://q-r.to/baeegN" :
                //celesta
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_celesta);
                intent.putExtra("ALAT",R.drawable.item_celesta);
                intent.putExtra("TITLE",R.drawable.title_celesta);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefT" :
                //cello
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_cello);
                intent.putExtra("ALAT",R.drawable.item_cello);
                intent.putExtra("TITLE",R.drawable.title_cello);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefh" :
                //klarinet
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_clarinet);
                intent.putExtra("ALAT",R.drawable.item_clarinet);
                intent.putExtra("TITLE",R.drawable.title_clarinet);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefW" :
                //contra bass
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_contra_bass);
                intent.putExtra("ALAT",R.drawable.item_contra_bass);
                intent.putExtra("TITLE",R.drawable.title_contrabass);
                startActivity(intent);
                break;
            case "http://q-r.to/baeegC" :
                //cymbal
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_cymbal);
                intent.putExtra("ALAT",R.drawable.item_cymbal);
                intent.putExtra("TITLE",R.drawable.title_cymbal);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefd" :
                //flute
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_flute);
                intent.putExtra("ALAT",R.drawable.item_flute);
                intent.putExtra("TITLE",R.drawable.title_flute);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefm" :
                //french horn
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_french_horn);
                intent.putExtra("ALAT",R.drawable.item_french_horn);
                intent.putExtra("TITLE",R.drawable.title_frenchhorn);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefc" :
                //harpa
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_harp);
                intent.putExtra("ALAT",R.drawable.item_harp);
                intent.putExtra("TITLE",R.drawable.title_harp);
                startActivity(intent);
                break;
            case "http://q-r.to/baeeff" :
                //obo
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_oboe);
                intent.putExtra("ALAT",R.drawable.item_oboe);
                intent.putExtra("TITLE",R.drawable.title_oboe);
                startActivity(intent);
                break;
            case "http://q-r.to/baeegQ" :
                //piano
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_piano);
                intent.putExtra("ALAT",R.drawable.item_piano);
                intent.putExtra("TITLE",R.drawable.title_piano);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefa" :
                //picollo
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_piccolo);
                intent.putExtra("ALAT",R.drawable.item_piccollo);
                intent.putExtra("TITLE",R.drawable.title_piccolo);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefz" :
                //snare drum
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_snare_drum);
                intent.putExtra("ALAT",R.drawable.item_snare_drum);
                intent.putExtra("TITLE",R.drawable.title_snaredrum);
                startActivity(intent);
                break;
            case "http://q-r.to/baeeg8" :
                //timpani
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_timpani);
                intent.putExtra("ALAT",R.drawable.item_timpani);
                intent.putExtra("TITLE",R.drawable.title_timpani);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefs" :
                //trombon
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_trombone);
                intent.putExtra("ALAT",R.drawable.item_trombone);
                intent.putExtra("TITLE",R.drawable.title_trombone);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefq" :
                //trumpet
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_trumpet);
                intent.putExtra("ALAT",R.drawable.item_trumpet);
                intent.putExtra("TITLE",R.drawable.title_trumpet);
                startActivity(intent);
                break;
            case "http://q-r.to/baeefx" :
                //tuba
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_tuba);
                intent.putExtra("ALAT",R.drawable.item_tuba);
                intent.putExtra("TITLE",R.drawable.title_tuba);
                startActivity(intent);
                break;
            case "http://q-r.to/baeegG" :
                //vibraphone
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_vibraphone);
                intent.putExtra("ALAT",R.drawable.item_vibraphone);
                intent.putExtra("TITLE",R.drawable.title_virafon);
                startActivity(intent);
                break;
            case "http://q-r.to/baeeey" :
                //viola
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_viola_alto);
                intent.putExtra("ALAT",R.drawable.item_viola);
                intent.putExtra("TITLE",R.drawable.title_biola_alto);
                startActivity(intent);
                break;
            case "http://q-r.to/baeeep" :
                //violin
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_violin);
                intent.putExtra("ALAT",R.drawable.item_violin);
                intent.putExtra("TITLE",R.drawable.title_biola);
                startActivity(intent);
                break;
            case "http://q-r.to/baeegF" :
                //xylophone
                intent = new Intent(this,MusicPlayerActivity.class);
                intent.putExtra("SOUND",R.raw.qr_xylophone);
                intent.putExtra("ALAT",R.drawable.item_xylophone);
                intent.putExtra("TITLE",R.drawable.title_silofon);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.stopCamera();
    }
}