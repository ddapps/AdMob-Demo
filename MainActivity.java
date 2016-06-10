package demo.ads.admob.admobdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bottomBannerAdButton = (Button) findViewById(R.id.button);
        bottomBannerAdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, BottomBannerAd.class);
                startActivity(intent);
            }
        });

        final Button topBannerAdButton = (Button) findViewById(R.id.button2);
        topBannerAdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, TopBannerAd.class);
                startActivity(intent);
            }
        });

        final Button interstitialAdButton = (Button) findViewById(R.id.button3);
        interstitialAdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final InterstitialAd mInterstitialAd;
                mInterstitialAd = new InterstitialAd(context);
                // set the ad unit ID
                mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
                AdRequest adRequest = new AdRequest.Builder().build(); //.addTestDevice("XXXXXXXX")
                mInterstitialAd.loadAd(adRequest);
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    }
                    public void onAdClosed() {
                        Toast.makeText(context,"On AdClosed handle called", Toast.LENGTH_SHORT).show();

                    }
                    public void onAdLeftApplication() {

                    }
                    public void onAdFailedToLoad(int var1) {
                        Toast.makeText(context,"On AdFailed to Load handle called", Toast.LENGTH_SHORT).show();
                    }


                });
            }
        });


        final Button codeSamplesButton = (Button) findViewById(R.id.button4);
        codeSamplesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        final InterstitialAd mInterstitialAd;
        mInterstitialAd = new InterstitialAd(this);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder().build(); //.addTestDevice("XXXXXXXX")
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
            public void onAdClosed() {
                exitFromApp();

            }
            public void onAdLeftApplication() {
            }
            public void onAdFailedToLoad(int var1) {
                exitFromApp();
            }

        });
    }

    private void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
