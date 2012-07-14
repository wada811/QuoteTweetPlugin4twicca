package jp.co.wada811.quotetweet.plugin4twicca;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class QuoteTweetPlugin4twiccaActivity extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart(){
        super.onStart();

        final Intent intent = getIntent();
        if(intent == null){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        final String action = intent.getAction();
        if(action.equals("jp.r246.twicca.ACTION_SHOW_TWEET")){
            final String statusId = intent.getStringExtra("id");
            final String screenName = intent.getStringExtra("user_screen_name");
            final String tweet = " QT https://twitter.com/" + screenName + "/status/" + statusId;
            tweet(this, tweet);
            setResult(RESULT_OK);
            finish();
        }else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    public static void tweet(Context context, String tweet){
        Uri.Builder builder = Uri.parse("https://twitter.com/intent/tweet").buildUpon();
        builder.appendQueryParameter("text", tweet); // WebIntentのパラメータ

        Uri web_intent_uri = builder.build();

        try{
            // twiccのツイート画面を呼び出し
            Intent intent = new Intent(Intent.ACTION_VIEW, web_intent_uri);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setPackage("jp.r246.twicca");
            context.startActivity(intent);
            return;
        }catch(ActivityNotFoundException e){
            // twiccaがインストールされていない

            // 必要ならブラウザを呼び出さずに独自のTwitter認証を呼び出すなどのカスタマイズを行うことが出来ます。

            // ブラウザでWebIntentを呼び出し
            Intent intent = new Intent(Intent.ACTION_VIEW, web_intent_uri);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            context.startActivity(intent);
            return;
        }
    }
}
