package hbaduk.gun_game;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class tut extends ActionBarActivity {
    Animation slide;
    ImageView img_slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tut);


        img_slide = (ImageView) findViewById(R.id.imageView30);
        slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tut_slide);

        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        img_slide.startAnimation(slide);
    }


}
