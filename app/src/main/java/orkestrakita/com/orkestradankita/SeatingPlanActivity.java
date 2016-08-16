package orkestrakita.com.orkestradankita;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;

import orkestrakita.com.orkestradankita.databinding.ActivitySeatingplanBinding;

/**
 * Created by irfananda on 29/07/16.
 */
public class SeatingPlanActivity extends AppCompatActivity{

    private boolean paused = true;
    private SeekBar seekBar;
    private MediaPlayer mPlayer = null;
    private boolean soundOn = true;
    private Handler mHandler = new Handler();
    private ActivitySeatingplanBinding binding;
    Drawable dr;
    Bitmap bitmap;
    Drawable d;

    private Runnable runn = new Runnable() {
        @Override
        public void run() {
            if(mPlayer != null){
                int mCurrentPosition = mPlayer.getCurrentPosition();
                seekBar.setProgress(mCurrentPosition);
            }
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seatingplan);
        binding.setVm(this);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mPlayer != null && fromUser){
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        runOnUiThread(runn);

        initAsset();
    }

    @Override
    protected void onDestroy() {
        if (mPlayer!=null){
            mHandler.removeCallbacks(runn);
            mPlayer.release();
        }
        super.onDestroy();
    }

    private void initAsset() {

        dr = getResources().getDrawable(R.drawable.bg_seatpln_chord);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 1000, true));
        binding.bg.setBackground(d);
        dr = getResources().getDrawable(R.drawable.home);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.back.setBackground(d);
        dr = getResources().getDrawable(R.drawable.sound_on);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
        dr = getResources().getDrawable(R.drawable.play);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
        dr = getResources().getDrawable(R.drawable.tempat_seatplan);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 500, true));
        binding.tempatSeatplan.setImageDrawable(d);
    }

    public void onClickSound(View view){
        if (soundOn) {
            SoundOff();
        }else {
            SoundOn();
        }
    }

    public void onClickHome(View view){
        finish();
    }

    public void onClickPlayorPause(View view){
        if (mPlayer!=null) {
            if (paused) {
                playMusic();
            } else {
                pauseMusic();
            }
        }
    }

    private void playMusic() {
        mPlayer.start();
        paused = false;
        dr = getResources().getDrawable(R.drawable.pause);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
    }

    private void pauseMusic() {
        mPlayer.pause();
        paused = true;
        dr = getResources().getDrawable(R.drawable.play);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
    }

    private void stopMusic() {
        paused = true;
        dr = getResources().getDrawable(R.drawable.play);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
    }

    private void setMusic(int resID) {
        if (mPlayer!=null) {
            if (mPlayer.isPlaying()) {
                pauseMusic();
                mPlayer.release();
            }
            stopMusic();
            mPlayer = MediaPlayer.create(this, resID);
        }else{
            mPlayer = MediaPlayer.create(this, resID);
        }
        seekBar.setMax(mPlayer.getDuration());
    }

    private void SoundOn() {
        AudioManager volumeControl = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setStreamVolume(AudioManager.STREAM_MUSIC, volumeControl.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        soundOn = true;
        dr = getResources().getDrawable(R.drawable.sound_on);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
    }

    private void SoundOff() {
        AudioManager volumeControl = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        soundOn = false;
        dr = getResources().getDrawable(R.drawable.sound_off);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
    }

    public void onClickPerkusi(View view){
        setMusic(R.raw.sp_perkusi);
    }

    public void onClickKlarinet(View view){
        setMusic(R.raw.sp_klarinet);
    }

    public void onClickTuba(View view){
        setMusic(R.raw.sp_tuba);
    }

    public void onClickObo(View view){
        setMusic(R.raw.sp_obo);
    }

    public void onClickpiccolo_flute(View view){
        setMusic(R.raw.sp_flute_piccolo);
    }

    public void onClickfrench_horn(View view){
        setMusic(R.raw.sp_french_horn);
    }

    public void onClickharpa(View view){
        setMusic(R.raw.sp_harp);
    }

    public void onClickcelesto(View view){
        setMusic(R.raw.sp_celesta);
    }

    public void onClicktrombon(View view){
        setMusic(R.raw.sp_trombon);
    }

    public void onClickpiano(View view){
        setMusic(R.raw.sp_piano);
    }

    public void onClickbiola12(View view){
        setMusic(R.raw.sp_biola_1_biola_2);
    }

    public void onClicktrompet(View view){
        setMusic(R.raw.sp_trompet);
    }

    public void onClickbiola_alto(View view){
        setMusic(R.raw.sp_biola_alto);
    }

    public void onClickcontra_bass(View view){
        setMusic(R.raw.sp_contra_bass);
    }

    public void onClickselo(View view){
        setMusic(R.raw.sp_selo);
    }

    public void onClickbassoon(View view){
        setMusic(R.raw.sp_bassoon);
    }
}
