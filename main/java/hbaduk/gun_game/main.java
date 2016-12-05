package hbaduk.gun_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

public class main extends Activity implements View.OnClickListener{
    ImageView strt,about,tut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        strt = (ImageView) findViewById(R.id.imageView26);
        tut = (ImageView) findViewById(R.id.imageView27);
        about = (ImageView) findViewById(R.id.imageView28);
        strt.setOnClickListener(this);
        tut.setOnClickListener(this);
        about.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView26){
            Intent gecis = new Intent(main.this,MainActivity.class);
            startActivity(gecis);
        } else if(v.getId() == R.id.imageView27){
            Intent gecis = new Intent(main.this,tut.class);
            startActivity(gecis);
        } else if(v.getId() == R.id.imageView28){
            Intent gecis = new Intent(main.this,about.class);
            startActivity(gecis);
        }
    }
}
