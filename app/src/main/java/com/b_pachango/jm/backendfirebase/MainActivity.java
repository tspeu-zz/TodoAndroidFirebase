package com.b_pachango.jm.backendfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private String FIREBASE_URL = "https://1todolistandroid.firebaseio.com/";
    private String FIREBASE_CHILD = "test";
    private EditText txt;
    private Button btn;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (EditText) findViewById(R.id.editText);
        btn =(Button) findViewById(R.id.buttonok);

        Firebase.setAndroidContext(this);
        firebase = new Firebase(FIREBASE_URL).child(FIREBASE_CHILD);



    }

    public void grabaEnFireBase(View v){

        firebase.setValue(txt.getText().toString());
        txt.setText("");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String msn = snapshot.getValue().toString();
                //aqui esta el valor
                Toast.makeText(MainActivity.this, msn, Toast.LENGTH_SHORT).show();
                Log.e(getLocalClassName(), snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

    }



}
