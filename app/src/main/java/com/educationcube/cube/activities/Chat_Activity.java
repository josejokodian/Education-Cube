package com.educationcube.cube.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.educationcube.cube.R;
import com.educationcube.cube.sdk.*;

import java.util.Locale;

public class Chat_Activity extends AppCompatActivity implements JivoDelegate {

    JivoSdk jivoSdk;
    EditText edit_aler_email;
    RelativeLayout relative_alert;
    RelativeLayout relative_webview_chat;
    Button btn_alert_ok;
    Button btn_alert_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);

        String lang = Locale.getDefault().getLanguage().indexOf("en") >= 0 ? "en" : "ru";
        jivoSdk = new JivoSdk((WebView) findViewById(R.id.webview), lang);

        jivoSdk.delegate=this;
        jivoSdk.prepare();

        initialize_Function();
        alert_Dialog();
    }

    private void initialize_Function() {
        edit_aler_email = findViewById(R.id.edit_aler_email);
        relative_alert = findViewById(R.id.relative_alert);

        relative_webview_chat = findViewById(R.id.relative_webview_chat);
        relative_webview_chat.setVisibility(View.GONE);

        btn_alert_ok = findViewById(R.id.btn_alert_ok);
        btn_alert_cancel = findViewById(R.id.btn_alert_cancel);

        edit_aler_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("chatpage", "Enter pressed");
                    donebuttonpressed();

                }
                return false;
            }
        });
    }

    private void alert_Dialog() {
        btn_alert_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donebuttonpressed();
            }
        });
        btn_alert_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void donebuttonpressed() {
        String user_email = edit_aler_email.getText().toString();
        if (isValidEmail(user_email)) {

            relative_alert.setVisibility(View.GONE);
            relative_webview_chat.setVisibility(View.VISIBLE);
        } else {
            edit_aler_email.setError("Email not valid");
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onEvent(String name, String data) {
        if (name.equals("url.click")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
            startActivity(browserIntent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Button btn_alertOption_ok=findViewById(R.id.btn_alertOption_ok);
        Button btn_alertOption_cancel=findViewById(R.id.btn_alertOption_cancel);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radiobtn_alertQ1:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radiobtn_alertQ2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobtn_alertQ3:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobtn_alertQ4:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobtn_alertQ5:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobtn_alertQother:
                if (checked)
                    // Ninjas rule
                    break;
        }
        if(checked){
            Log.d("chat",checked+"");
            btn_alertOption_ok.setEnabled(true);
        }else{
            Log.d("chat",checked+"");
            Toast.makeText(getApplicationContext(),"Please select an option to continue",Toast.LENGTH_SHORT).show();
        }
    }
}
