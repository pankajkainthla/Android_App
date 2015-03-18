package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by pankaj on 19-Mar-15.
 */
public class splash extends Activity {
    MediaPlayer ourSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ourSong = MediaPlayer.create(splash.this, R.raw.sadma);
        ourSong.start();


        Thread timer = new Thread() {

            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();

                } finally {

                    ourSong.stop();
                    Intent openStartingPoint = new Intent("travis.thenewboston.com.thenewboston.Menu");
                    startActivity(openStartingPoint);
                }


            }


        };

        timer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.stop();
        finish();

    }
}
