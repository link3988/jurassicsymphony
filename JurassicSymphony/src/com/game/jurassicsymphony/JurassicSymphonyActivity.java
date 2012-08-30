package com.game.jurassicsymphony;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class JurassicSymphonyActivity extends Activity {
	
	private static TextView pointsText;
	public static Button play;
	public static ImageButton dino1;
	public MediaPlayer mp;
	public MediaPlayer dinoSound;
	private int score = 0;
	public boolean canPress = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mp = MediaPlayer.create(this, R.raw.parktheme);
        dinoSound = MediaPlayer.create(this, R.raw.raptor);
        
        pointsText = (TextView) findViewById(R.id.points);
        resetScore();
        
        play = (Button) findViewById(R.id.control);
        addListenerOnPlayButton();
        
        //Dino button listeners that add score.
        dino1 = (ImageButton) findViewById(R.id.imageButton1);
        addDino1Listener();
    }
    
	public void addListenerOnPlayButton() {
		 		
		play.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				//mediaplayer play
				//if text us play, lay, if its stop,stop.
				if(play.getText().equals("Play")) {
					resetScore();
					play.setText("Stop");
					playMusic();
				} else {
					play.setText("Play");
					mp.stop();
				}
			}
 
		}); 
	}
	
	public void addDino1Listener() {
 		
		dino1.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				if(canPress) {
					canPress = false;
					playDino1Sound();
					dinoSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			            @Override
			            public void onCompletion(MediaPlayer mp) {
			                            canPress = true;
			                    		score += 50;
			                    		pointsText.setText(""+score);
			            }
			        });
				}
			}
 
		}); 
	}
	
	public void playMusic() {
		
		mp = MediaPlayer.create(this, R.raw.parktheme);
		mp.start();
	}
	
	public void playDino1Sound() {
		dinoSound = MediaPlayer.create(this, R.raw.raptor);
		dinoSound.start();
	}
	
	public void resetScore() {
		score = 0;
		pointsText.setText(""+score);
	}
}