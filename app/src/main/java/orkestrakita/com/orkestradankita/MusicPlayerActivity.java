package orkestrakita.com.orkestradankita;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import orkestrakita.com.orkestradankita.databinding.ActivityMusicplayerBinding;

/**
 * Created by irfananda on 29/07/16.
 */
public class MusicPlayerActivity extends AppCompatActivity {

    Drawable dr;
    Bitmap bitmap;
    Drawable d;
    private ActivityMusicplayerBinding binding;
    private int resDraw;
    private boolean paused = true;
    private SeekBar seekBar;
    private MediaPlayer mPlayer = null;
    private boolean soundOn = true;
    private Handler mHandler = new Handler();
    private int SOUND;
    private int ALAT;
    private int TITLE;
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_musicplayer);
        binding.setVm(this);

        SOUND = getIntent().getIntExtra("SOUND",0);
        ALAT = getIntent().getIntExtra("ALAT",0);
        TITLE = getIntent().getIntExtra("TITLE",0);

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


        if (SOUND!=0)
            setMusic(SOUND);
        else
            finish();

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
        binding.alatmusik.setImageDrawable(getResources().getDrawable(ALAT));
        binding.titleMusic.setImageDrawable(getResources().getDrawable(TITLE));
//        dr = getResources().getDrawable(TITLE);
//        bitmap = ((BitmapDrawable) dr).getBitmap();
//        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 500, 400, true));
//        binding.titleMusic.setImageDrawable(d);
        dr = getResources().getDrawable(R.drawable.bg_menu_dan_alatmusik);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 400, 800, true));
        binding.bg.setBackground(d);
        dr = getResources().getDrawable(R.drawable.home2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.back.setBackground(d);
        dr = getResources().getDrawable(R.drawable.sound_on2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
        dr = getResources().getDrawable(R.drawable.play2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
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
        dr = getResources().getDrawable(R.drawable.pause2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
    }

    private void pauseMusic() {
        mPlayer.pause();
        paused = true;
        dr = getResources().getDrawable(R.drawable.play2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.play.setBackground(d);
    }

    private void stopMusic() {
        paused = true;
        dr = getResources().getDrawable(R.drawable.play2);
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
        dr = getResources().getDrawable(R.drawable.sound_on2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
    }

    private void SoundOff() {
        AudioManager volumeControl = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volumeControl.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        soundOn = false;
        dr = getResources().getDrawable(R.drawable.sound_off2);
        bitmap = ((BitmapDrawable) dr).getBitmap();
        d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        binding.sound.setBackground(d);
    }
}
