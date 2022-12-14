package com.example.hospital_try_1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class CustomDialogClass extends Dialog  {


     Activity c;
     Dialog d;
     Button yes, no;

    Intent mainactivity_intent;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coustm_logout_dialog);

         mainactivity_intent=new Intent(c,MainActivity.class);

        yes = (Button) findViewById(R.id.yes_bt);
        no = (Button) findViewById(R.id.no_bt);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveSharedPreference.clearUserName(c);
                c.startActivity(mainactivity_intent);
                c.finish();
                Toast.makeText(c,"Logout Successful",Toast.LENGTH_SHORT).show();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


}
