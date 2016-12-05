package hbaduk.gun_game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity implements View.OnTouchListener, View.OnClickListener{
    private ImageView imgslide,imgtrisec,imgmagsec,imgtri,imgmagazine;
    FrameLayout slideframe,triframe,btn_sesli,frakovan,frame_contents,frame_44,contentscale;
    TextView text2,text3,text4,text5,text6;
    float x = 0;
    float rootX,slideilkdokunus,slidedokunma,slidekaldirma,maxright;
    float tetikilkdokunus,slidedokunmaT;
    static TranslateAnimation geridon,magazinecikarma,magazinetakma,atesSlide1,atesSlide2;
    static RotateAnimation tetikgeri;
    public static RotateAnimation kilitle,kilidiac;
    public float rootaci;
    public static boolean slide_kilit_durumu = true,magazine_kilit_durumu = false,kurma_bekleme = false, trigger_kilit_durumu = false, atesSonrasi = true;
    CountDownTimer countEff,count_kurma_bekmele;
    public ImageView img_slide_kilit,img22,img23;
    ImageView btnfill;
    public static boolean namlu=false,menu_status = false;
    public static int mermi = 0;
    int screenWidth = 0;
    public Animation animation,animfirlatma,anim_tepme,anim_tepme_hor;
    public LinearLayout root_layout;
    SoundPool soundPool;
    AudioManager audioManager;
    int sound_ates,sound_bosmermi,sound_magazine_on,sound_magazine_off,sound_kursunalma,sound_slidesec,sound_triggersec,sound_kurma;
    public ImageView img_voice,img_gecis,img_tut;
    private GestureDetector detector;
    public boolean hor_ = false,shake_status = false;
    final int clr_green= Color.rgb(52,176,36),clr_red = Color.rgb(243,41,41);

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    private InterstitialAd gecisreklam;
    private AdRequest adrequ;

    public void loadGecis(){
        gecisreklam.loadAd(adrequ);
    }
    public void showGecis(){
        if(gecisreklam.isLoaded()){
            gecisreklam.show();
        }else{

        }
    }

    @Override
    public void onBackPressed() {
        menu_status = false;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        showGecis();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adrequ = new AdRequest.Builder().build();
        gecisreklam = new InterstitialAd(this);
        gecisreklam.setAdUnitId("ca-app-pub-1583132169815895/5756935324");
        gecisreklam.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadGecis();
            }

            @Override
            public void onAdLoaded() {
            }
        });
        loadGecis();

        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        root_layout = (LinearLayout) findViewById(R.id.root);
        frakovan = (FrameLayout) findViewById(R.id.flame3);
        frame_contents = (FrameLayout) findViewById(R.id.contents);

        slideframe = (FrameLayout) findViewById(R.id.fraslide);
        triframe = (FrameLayout) findViewById(R.id.fratri1);
        frame_44 = (FrameLayout) findViewById(R.id.fra44);
        contentscale = (FrameLayout) findViewById(R.id.contents_scale);
        btn_sesli = (FrameLayout) findViewById(R.id.fra24b);
        btn_sesli.setOnClickListener(this);
        img_slide_kilit = (ImageView) findViewById(R.id.imageView9a);
        img_voice = (ImageView) findViewById(R.id.imageView12);
        img_voice.setOnClickListener(this);
        img_gecis = (ImageView) findViewById(R.id.imageView13);
        img_gecis.setOnClickListener(this);
        img_tut = (ImageView) findViewById(R.id.imageView11a);
        img_tut.setOnClickListener(this);
        imgtrisec = (ImageView) findViewById(R.id.imageView14);
        imgtrisec.setDrawingCacheEnabled(true);
        imgtrisec.setOnTouchListener(this);
        imgmagsec = (ImageView) findViewById(R.id.imageView18);
        imgmagsec.setDrawingCacheEnabled(true);
        imgmagsec.setOnTouchListener(this);
        imgmagazine = (ImageView) findViewById(R.id.imageView20);
        imgslide = (ImageView) findViewById(R.id.imageView21);
        imgtri = (ImageView) findViewById(R.id.imageView16);
        img22 = (ImageView) findViewById(R.id.imageView22);
        img23 = (ImageView) findViewById(R.id.imageView23);

        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        text6 = (TextView) findViewById(R.id.textView6);
        btnfill = (ImageView) findViewById(R.id.button);
        btnfill.setOnClickListener(this);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        screenWidth = slideframe.getWidth();
        slideKilitle();


        detector= new GestureDetector(this, new detectorClass() );
        frame_44.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });





        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.belirme);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img22.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });



        animfirlatma = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.firlatma);
        animfirlatma.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                img23.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


        anim_tepme = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tepme);
        anim_tepme.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


        anim_tepme_hor = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tepme_hor);
        anim_tepme_hor.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });



        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //the maximum number of simultaneous streams for this SoundPool object
        int maxStreams = 4;
        //the audio stream type as described in AudioManager
        int streamType = AudioManager.STREAM_MUSIC;
        //the sample-rate converter quality. Currently has no effect. Use 0 for the default.
        int srcQuality = 0;

        soundPool = new SoundPool(maxStreams, streamType, srcQuality);
        sound_ates = soundPool.load(this, R.raw.fire_normalize, 1);
        sound_bosmermi = soundPool.load(this, R.raw.bos_ates_normalize, 1);
        sound_magazine_off = soundPool.load(this, R.raw.magazineoff, 1);
        sound_magazine_on  = soundPool.load(this, R.raw.magazineon, 1);
        sound_kursunalma   = soundPool.load(this, R.raw.kursundoldurma, 1);
        sound_slidesec     = soundPool.load(this, R.raw.slide_kilit_normalize, 1);
        sound_triggersec   = soundPool.load(this, R.raw.trigger_kilit_normalize, 1);
        sound_kurma        = soundPool.load(this, R.raw.kurma_normalize, 1);
        triframe.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
        if(trigger_kilit_durumu==true){
            return false;
        }

        float tetikMesafe = 0;
        rootaci = imgtri.getRotation();

        switch (event.getAction() & MotionEvent.ACTION_MASK ) {
             case MotionEvent.ACTION_DOWN:
                 atesSonrasi = true;
                 tetikilkdokunus = event.getRawX()-triframe.getX();
                 slidedokunmaT = event.getRawX();

                  break;
            case MotionEvent.ACTION_MOVE:

                if(hor_ == false){
                    if((event.getRawX()-triframe.getX()) <= tetikilkdokunus){

                    } else {
                        tetikMesafe = (event.getRawX()-triframe.getX()) - tetikilkdokunus;

                        screenWidth = slideframe.getWidth();
                        int aci = (int) tetikMesafe*480/screenWidth;

                        imgtri.setPivotX(50f);
                        imgtri.setPivotY(0f);

                        if(atesSonrasi == true){
                            imgtri.setRotation((-1) * aci);
                        }

                        if(imgtri.getRotation()<-40){

                            atesSonrasi = false;
                            imgtri.setAnimation(null);
                            tetikgeri = new RotateAnimation(rootaci, 0, 50, 0);
                            tetikgeri.setDuration(200);
                            tetikgeri.setInterpolator(new AccelerateInterpolator());
                            imgtri.startAnimation(tetikgeri);
                            imgtri.setRotation(0);
                            atesEt(0);
                        }
                    }
                }else {
                    if((event.getRawX()-triframe.getX()) >= tetikilkdokunus){

                    } else {
                        tetikMesafe = (event.getRawX()-triframe.getX()) - tetikilkdokunus;

                        screenWidth = slideframe.getWidth();
                        int aci = (int) tetikMesafe*480/screenWidth;

                        imgtri.setPivotX(50f);
                        imgtri.setPivotY(0f);

                        if(atesSonrasi == true){
                            imgtri.setRotation(aci);
                        }

                        if(imgtri.getRotation()<-40){

                            atesSonrasi = false;
                            imgtri.setAnimation(null);
                            tetikgeri = new RotateAnimation(rootaci, 0, 50, 0);
                            tetikgeri.setDuration(200);
                            tetikgeri.setInterpolator(new AccelerateInterpolator());
                            imgtri.startAnimation(tetikgeri);
                            imgtri.setRotation(0);
                            atesEt(0);
                        }
                    }
                }









                break;


             case MotionEvent.ACTION_UP:
                 imgtri.setAnimation(null);
                 tetikgeri = new RotateAnimation(rootaci, 0, 50, 0);
                 tetikgeri.setDuration(200);
                 tetikgeri.setInterpolator(new AccelerateInterpolator());
                 imgtri.startAnimation(tetikgeri);
                 imgtri.setRotation(0);

                  break;
             case MotionEvent.ACTION_POINTER_DOWN:

                 break;
             case MotionEvent.ACTION_POINTER_UP:

                 break;

        }

        return true;
        }
        }

        );

        slideframe.setOnTouchListener(new View.OnTouchListener() {
            @Override
                public boolean onTouch(View v, MotionEvent event) {
                if(slide_kilit_durumu==true){
                    return false;
                }
                screenWidth = slideframe.getWidth();
                float fgen = (float) screenWidth;
                maxright = ((fgen *13)/80);
                rootX = 0;

                switch (event.getAction() & MotionEvent.ACTION_MASK ) {
                    case MotionEvent.ACTION_DOWN:
                        slideilkdokunus = event.getRawX()-imgslide.getX();
                        slidedokunma = event.getRawX();
                        slidekaldirma = slidedokunma;
                        break;

                    case MotionEvent.ACTION_MOVE:

                        if(hor_ == false){
                            if(event.getRawX()<= slidedokunma){

                            }else if(event.getRawX() >= slidedokunma+maxright){
                                slideframe.setX(maxright);
                                if(kurma_bekleme==false){
                                    SoundCalar(sound_kurma);
                                    kurma_bekleme=true;
                                    count_kurma_bekmele= new CountDownTimer(1500,1500) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }
                                        @Override
                                        public void onFinish() {
                                            kurma_bekleme=false;
                                            count_kurma_bekmele.cancel();
                                        }
                                    }.start();
                                }
                                if(namlu==false){
                                    if(magazine_kilit_durumu==false){
                                        if(mermi>0){
                                            if(namlu==false){
                                                mermi = mermi-1;
                                                namlu=true;
                                            }
                                            text6.setText("Barrel:Filled");

                                            text5.setText("Bullet: "+mermi);
                                        }
                                    }
                                } else {
                                    if(mermi>0){
                                        text5.setText("Bullet: "+mermi);
                                    }
                                }
                            } else {
                                x= event.getRawX()-slideilkdokunus;
                                int intx = (int)  x;
                                slideframe.setX(intx);
                                slidekaldirma = event.getRawX();
                            }
                        }else {
                            if(event.getRawX() >= slidedokunma){

                            }else if(event.getRawX() <= slidedokunma-maxright){
                                slideframe.setX(maxright);
                                if(kurma_bekleme==false){
                                    SoundCalar(sound_kurma);
                                    kurma_bekleme=true;
                                    count_kurma_bekmele= new CountDownTimer(1500,1500) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                        }
                                        @Override
                                        public void onFinish() {
                                            kurma_bekleme=false;
                                        }
                                    }.start();
                                }
                                if(namlu==false){
                                    if(magazine_kilit_durumu==false){
                                        if(mermi>0){
                                            if(namlu==false){
                                                mermi = mermi-1;
                                                namlu=true;
                                            }
                                            text6.setText("Barrel:Filled");

                                            text5.setText("Bullet: "+mermi);
                                        }
                                    }
                                } else {
                                    if(mermi>0){
                                        text5.setText("Bullet: "+mermi);
                                    }
                                }
                            } else {
                                if(hor_ == false){
                                    x= event.getRawX()-slideilkdokunus;
                                }else {
                                    x= slideilkdokunus-event.getRawX();
                                }
                                int intx = (int)  x;
                                slideframe.setX(intx);
                                slidekaldirma = event.getRawX();
                            }
                        }











                        break;

                    case MotionEvent.ACTION_UP:
                        slideframe.setAnimation(null);
                        float mesafe = slidekaldirma-slideilkdokunus;
                        if(hor_ == false){
                            geridon = new TranslateAnimation(mesafe,0,0,0);
                        }else {
                            geridon = new TranslateAnimation((-1)*mesafe,0,0,0);
                        }


                        geridon.setDuration(80);
                        geridon.setInterpolator(new AccelerateInterpolator());
                        slideframe.startAnimation(geridon);
                        slideframe.setX(rootX);
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                }
                slideframe.invalidate();
                return true;
            }
            }

        );


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        scaleIslemi();
    }


    private final SensorEventListener mSensorListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];float y = se.values[1];float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;

            if(shake_status==true){
                if(mAccel>3.5 || mAccel<-3.5 ){
                atesEt(1);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };





    private void scaleIslemi() {
        double genislik = (double) contentscale.getWidth();
        double boy = (double)contentscale.getHeight();
        double oran = genislik/boy;

        if(oran<1.6){
            double mutlakboy = genislik/(1.6);
            contentscale.getLayoutParams().height = (int) mutlakboy;
        } else if(oran>1.6){
            double mutlakgenislik = boy*(1.6);
            contentscale.getLayoutParams().width = (int) mutlakgenislik;
        }
    }

    public void atesEt(int a) {
        //a == 0 tetik ile
        //a == 1 tus ile

        if(slide_kilit_durumu==false){
            if(namlu==true){
                int gen = imgslide.getWidth();
                float fgen = (float) gen;
                maxright = ((fgen *13)/80);
                img22.setVisibility(View.VISIBLE);
                img22.startAnimation(animation);
                img23.setVisibility(View.VISIBLE);
                img23.startAnimation(animfirlatma);
                if(hor_ == false){
                    frame_contents.startAnimation(anim_tepme);
                }else {
                    frame_contents.startAnimation(anim_tepme_hor);
                }

                titret();

                namlu=false;
                text6.setText("Barrel: Empty");
                slideframe.setAnimation(null);
                atesSlide1 = new TranslateAnimation(0,maxright,0,0);
                atesSlide1.setDuration(60);
                atesSlide1.setFillAfter(true);
                atesSlide1.setInterpolator(new AccelerateInterpolator());
                atesSlide2 = new TranslateAnimation(maxright,0,0,0);
                atesSlide2.setDuration(60);
                atesSlide2.setFillAfter(true);
                atesSlide2.setInterpolator(new AccelerateInterpolator());
                AnimationSet animset = new AnimationSet(false);
                animset.addAnimation(atesSlide1);
                animset.addAnimation(atesSlide2);
                slideframe.startAnimation(animset);

                if(a==1){    //atesleme buton ile veya sensorden gelirse
                    imgtri.setAnimation(null);
                    imgtri.setPivotX(50f);
                    imgtri.setPivotY(0f);
                    RotateAnimation r1 = new RotateAnimation(0, -40, 50, 0);
                    RotateAnimation r2 = new RotateAnimation(-40, 0, 50, 0);
                    r1.setDuration(40); r2.setDuration(40);
                    r1.setFillAfter(true); r2.setFillAfter(true);
                    r1.setInterpolator(new AccelerateInterpolator());
                    r2.setInterpolator(new LinearInterpolator());
                    AnimationSet animset1 = new AnimationSet(false);
                    animset1.addAnimation(r1);
                    animset1.addAnimation(r2);
                    imgtri.startAnimation(animset1);
                }

                if(magazine_kilit_durumu==false){
                    if(mermi>0){
                        mermi = mermi-1;
                        namlu=true;
                        text6.setText("Barrel:Filled");
                    }
                }
                SoundCalar(sound_ates);
                text5.setText("Bullet: "+mermi);
            }else{
                //bos at
                SoundCalar(sound_bosmermi);
                text5.setText("Bullet: "+mermi);
            }
        }
    }

    private void titret() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }

    private void SoundCalar(int ses) {
        float vol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = vol/maxVol;
        float rightVolume = vol/maxVol;
        int priority = 1;
        int no_loop = 0;
        float normal_playback_rate = 1f;
        soundPool.play(ses, leftVolume, rightVolume, priority, no_loop, normal_playback_rate);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                if(v.getId() == R.id.imageView14){
                    Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
                    int color = bmp.getPixel((int) event.getX(), (int) event.getY());
                    if (color == Color.TRANSPARENT) {
                        //Toast.makeText(getApplication(),"Tiklanan yer bos", Toast.LENGTH_SHORT).show();
                    }else {
                        if(trigger_kilit_durumu == false){
                            text3.setText("Trigger Lock:OFF");
                            text3.setTextColor(clr_red);
                            trigger_kilit_durumu = true;
                        }else {
                            text3.setText("Trigger Lock: ON");
                            text3.setTextColor(clr_green);
                            trigger_kilit_durumu = false;
                        }
                        SoundCalar(sound_triggersec);

                    }
                }

                if(v.getId() == R.id.imageView18){
                    Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
                    int color = bmp.getPixel((int) event.getX(), (int) event.getY());
                    if (color == Color.TRANSPARENT) {
                        //Toast.makeText(getApplication(),"Tiklanan yer bos", Toast.LENGTH_SHORT).show();
                    }else {
                        if(magazine_kilit_durumu == false){
                            magazineCikar();
                        }else {
                            magazineTak();
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

    //animasyonlar

    public void magazineCikar(){
        btnfill.setVisibility(View.VISIBLE);
        text4.setText("Mag.: Detached");
        text4.setTextColor(clr_red);
        magazine_kilit_durumu = true;
        SoundCalar(sound_magazine_off);
        imgmagazine.setAnimation(null);
        magazinecikarma = new TranslateAnimation(0,50,0,100);
        magazinecikarma.setDuration(100);
        magazinecikarma.setFillAfter(true);
        magazinecikarma.setInterpolator(new LinearInterpolator());
        imgmagazine.startAnimation(magazinecikarma);
    }

    public void magazineTak(){
        btnfill.setVisibility(View.INVISIBLE);
        text4.setText("Mag.: Attached");
        text4.setTextColor(clr_green);
        magazine_kilit_durumu = false;
        SoundCalar(sound_magazine_on);
        imgmagazine.setAnimation(null);
        magazinetakma = new TranslateAnimation(50,0,100,0);
        magazinetakma.setDuration(300);
        magazinetakma.setFillAfter(true);
        magazinetakma.setInterpolator(new AccelerateInterpolator());
        imgmagazine.startAnimation(magazinetakma);
    }

    public void slideKilitle(){
        kilitle = new RotateAnimation(0,30,Animation.RELATIVE_TO_SELF,0.58f,Animation.RELATIVE_TO_SELF,0.56f);
        kilitle.setDuration(100);
        kilitle.setInterpolator(new LinearInterpolator());
        kilitle.setFillAfter(true);
        img_slide_kilit.startAnimation(kilitle);
    }

    public void slideKilitac(){
        kilidiac = new RotateAnimation(30,0,Animation.RELATIVE_TO_SELF,0.58f,Animation.RELATIVE_TO_SELF,0.56f);
        kilidiac.setDuration(100);
        kilidiac.setInterpolator(new LinearInterpolator());
        kilidiac.setFillAfter(true);
        img_slide_kilit.startAnimation(kilidiac);
    }

    @Override
    public void onClick(View v) {
        //tiklanan slide secure
        if(v.getId() == R.id.fra24b){
            if(slide_kilit_durumu == false){
                slideKilitle();
                text2.setText("Slide Lock:OFF");
                text2.setTextColor(clr_red);
                slide_kilit_durumu = true;
            }else {
                slideKilitac();
                text2.setText("Slide Lock: ON");
                text2.setTextColor(clr_green);
                slide_kilit_durumu = false;
            }
            SoundCalar(sound_slidesec);
        }else if(v.getId() == R.id.button){
            if(mermi>0){
                Toast.makeText(getApplicationContext(),"You have bullet already!",Toast.LENGTH_SHORT).show();
            }else{
                mermi=12;
                SoundCalar(sound_kursunalma);
                text5.setText("Bullet: "+mermi);
            }
        }else if(v.getId() == R.id.imageView12){
            if(menu_status==false){
                initiatePopupWindow();
                menu_status = true;
            }
        }else if(v.getId() == R.id.imageView13){
            if(hor_ == false){
                hor_ = true;
                frame_contents.setRotationY(180.0f);
            } else {
                hor_ = false;
                frame_contents.setRotationY(0.0f);
            }


        }else if(v.getId() == R.id.imageView11a){
            Intent gec = new Intent(MainActivity.this,tut.class);
            startActivity(gec);
        }
    }


    private void initiatePopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup, null);
            final PopupWindow pwindo = new PopupWindow(popupView,LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            pwindo.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            Button btnClosePopup = (Button) popupView.findViewById(R.id.button2);
            btnClosePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menu_status = false;
                    pwindo.dismiss();
                }
            });

            final RadioButton hor_1 = (RadioButton) popupView.findViewById(R.id.radioButton);
            final RadioButton hor_2 = (RadioButton) popupView.findViewById(R.id.radioButton2);

            final RadioButton sensor_on = (RadioButton) popupView.findViewById(R.id.radioButton3);
            final RadioButton sensor_off = (RadioButton) popupView.findViewById(R.id.radioButton4);
            final TextView desc = (TextView) popupView.findViewById(R.id.textView10);

            if(hor_ == false){
                hor_1.setChecked(true);
            }else {
                hor_2.setChecked(true);
            }
            if(shake_status == false){
                sensor_on.setChecked(true);
            }else {
                sensor_off.setChecked(true);
            }

            RadioGroup rGroup = (RadioGroup)  popupView.findViewById(R.id.rgroup1);
            RadioGroup rGroup2 = (RadioGroup)  popupView.findViewById(R.id.rgroup2);


            rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(hor_1.isChecked()){
                        hor_ = false;
                        frame_contents.setRotationY(0.0f);
                    } else if (hor_2.isChecked()) {
                        hor_ = true;
                        frame_contents.setRotationY(180.0f);
                    }
                }
            });

            rGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(sensor_on.isChecked()){
                        shake_status = false;
                        desc.setText("Description : Sensor detector Off!");
                    } else if (sensor_off.isChecked()) {
                        shake_status = true;
                        desc.setText("Description : You can fire with shake the phone");
                    }
                }
            });





            SeekBar seekbar =(SeekBar) popupView.findViewById(R.id.seekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

            seekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));//bu satiri ben ekledim
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class detectorClass extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent ev) {

            return true;
        }
        @Override
        public void onShowPress(MotionEvent ev) {}

        @Override
        public void onLongPress(MotionEvent ev) {}

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {return true;
        }

        @Override
        public boolean onDown(MotionEvent ev) {return true;}

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float sensitivity = 50;
            if(e1.getY() - e2.getY() > sensitivity){
                //swipe up
                if(magazine_kilit_durumu==true){
                    magazineTak();
                }
                return true;
            }
            else if(e2.getY() - e1.getY() > sensitivity){
                //swipe down
                if(magazine_kilit_durumu==false){
                    magazineCikar();
                }
                return true;
            }
            else if(e1.getX() - e2.getX() > sensitivity){
                //left
                return true;
            }
            else if(e2.getX() - e1.getX() > sensitivity){
                //right
                return true;
            }
            else{
                //exception
                return true;
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            atesEt(1);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if(magazine_kilit_durumu==false){
                magazineCikar();
            } else {
                magazineTak();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}