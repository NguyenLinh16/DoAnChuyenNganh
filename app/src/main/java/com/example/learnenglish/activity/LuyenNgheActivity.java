package com.example.learnenglish.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.learnenglish.R;
import com.example.learnenglish.model.LuyenNghe;
import com.google.android.gms.measurement.internal.zzbh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LuyenNgheActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong,txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPrev, btnPlay, btnStop, btnNext;
    ArrayList<LuyenNghe> arrayList;
    int position = 0;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luyen_nghe);

        txtTimeSong = findViewById(R.id.textViewTimeSong);
        txtTitle = findViewById(R.id.textViewTitle);
        txtTimeTotal = findViewById(R.id.textViewTimeTotal);

        skSong = findViewById(R.id.seekBarSong);

        btnNext = findViewById(R.id.imageButtonNext);
        btnPlay = findViewById(R.id.imageButtonPlay);
        btnPrev = findViewById(R.id.imageButtonPrev);
        btnStop = findViewById(R.id.imageButtonStop);

        AddAudio();
        KhoiTaoMediaPlayer();

        mediaPlayer = MediaPlayer.create(LuyenNgheActivity.this, arrayList.get(position).getFile());
        txtTitle.setText(arrayList.get(position).getTitle());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    //Neu dang phat -> pause ->doi hinh play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }else {
                    //dang ngung -> phat -> doi hinh pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.baseline_pause_24);
                }
                SetTimeTotal();
                UpdateTimeSong();
            }

        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                KhoiTaoMediaPlayer();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position <0){
                    position = arrayList.size() -1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.baseline_pause_24);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position >arrayList.size() -1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.baseline_pause_24);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });

    }

    private void UpdateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                // update progress skSong
                skSong.setProgress(mediaPlayer.getCurrentPosition());

                // Kiểm tra xem thời gian audio -> nếu kết thúc -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position >arrayList.size() -1){
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.baseline_pause_24);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this, 500);
            }
        },100);
    }

    private void SetTimeTotal(){
        SimpleDateFormat dinhdangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhdangGio.format(mediaPlayer.getDuration()));
        // gán max của SkSong = mediaPlayer.getDuration()
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(LuyenNgheActivity.this, arrayList.get(position).getFile());
        txtTitle.setText(arrayList.get(position).getFile());
    }

    private void AddAudio() {
        arrayList = new ArrayList<>();
        arrayList.add(new LuyenNghe("Lession 1",R.raw.audio1));
        arrayList.add(new LuyenNghe("Lession 2",R.raw.audio2));
        arrayList.add(new LuyenNghe("Lession 3",R.raw.audio1));

    }
}