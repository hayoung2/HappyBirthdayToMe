package with.dee2.happybirthday.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import with.dee2.happybirthday.R;


public class MusicFragment extends Fragment {

    private SeekBar playerSeekBar;
    private CardView playPauseCard;
    private MediaPlayer mediaPlayer;
    private TextView startTime,endTime,title,name;
    private ImageView music_img,playPauseImg,nextBtn,prevBtn;
    private Handler handler=new Handler();
    private int cuttentPosition=0;
    private Uri[] uris={Uri.parse("android.resource://with.dee2.happybirthday/"+R.raw.happy_birthday1), Uri.parse("android.resource://with.dee2.happybirthday/"+R.raw.birthday2)
    , Uri.parse("android.resource://with.dee2.happybirthday/"+R.raw.birthday3)};
    private String[] Titles={"생일축하곡1","생일축하곡2","생일축하곡3"};
    private String[] names={"NCS","BGM25","danmoo"};


    private int[] imges={R.drawable.music_background_1,R.drawable.music_background_1,R.drawable.music_background_1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_music, container, false);

        init(v);


        playPauseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playPauseImg.setImageResource(R.drawable.ic_play_white);
                }else{
                    mediaPlayer.start();
                    playPauseImg.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });

        playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar seekBar=(SeekBar)view;
                int playPosition =(mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                startTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));

                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                playerSeekBar.setSecondaryProgress(i);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playerSeekBar.setProgress(0);
                playPauseImg.setImageResource(R.drawable.ic_play);
                startTime.setText("00:00");
                endTime.setText("00:00");

                mediaPlayer.reset();
                changeData();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cuttentPosition!=0){
                    cuttentPosition--;
                    changeData();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cuttentPosition!=2){
                    cuttentPosition++;
                    changeData();
                }
            }
        });

        prepareMediaPlayer(Uri.parse("android.resource://with.dee2.happybirthday/"+R.raw.birthday2));

        return v;
    }

    private void changeData() {
        prepareMediaPlayer(uris[cuttentPosition]);
        music_img.setImageResource(imges[cuttentPosition]);
        title.setText(Titles[cuttentPosition]);
        name.setText(names[cuttentPosition]);
    }
    private void init(View v) {


        playPauseCard =v.findViewById(R.id.playPauseCard);
        playPauseImg =v.findViewById(R.id.playPauseImg);
        nextBtn =v.findViewById(R.id.nextBtn);
        prevBtn =v.findViewById(R.id.previousBtn);

        music_img=v.findViewById(R.id.music_img);
        music_img.setImageResource(imges[cuttentPosition]);
        playerSeekBar=v.findViewById(R.id.playerSeekBar);
        startTime=v.findViewById(R.id.startTime);
        endTime=v.findViewById(R.id.endTime);
        title=v.findViewById(R.id.music_title);
        title.setText(Titles[cuttentPosition]);

        name=v.findViewById(R.id.music_name);
        name.setText(names[cuttentPosition]);

        mediaPlayer=new MediaPlayer();

        playerSeekBar.setMax(100);
    }

    private void prepareMediaPlayer(Uri uri) {
        try{
            mediaPlayer.setDataSource(getContext(),uri);
            mediaPlayer.prepare();
            endTime.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }catch (Exception e){
            Log.d("오류 ",e.toString());
        }
    }

    private Runnable updater =new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration =mediaPlayer.getCurrentPosition();
            startTime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar() {
        if(mediaPlayer.isPlaying()){
            playerSeekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }

    private String milliSecondsToTimer(long milliSeconds) {
        String timerString ="";
        String secondsString;

        int hours =(int)(milliSeconds/(1000*60*60));
        int minutes =(int)(milliSeconds%(1000*60*60))/(1000*60);
        int seconds =(int)((milliSeconds%(1000*60*60))%(1000*60)/1000);

        if(hours>0){
            timerString =hours +":";
        }

        if(seconds<10){
            secondsString="0"+seconds;
        }else{
            secondsString=""+seconds;
        }

        timerString =timerString+minutes+":"+secondsString;
        return timerString;
    }


}