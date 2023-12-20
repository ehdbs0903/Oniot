package org.techtown.oniot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.regex.Pattern;

public class FindActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private  Button bt_cancel, btn_send;
    private EditText find_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        mFirebaseAuth= FirebaseAuth.getInstance();

        bt_cancel = findViewById( R.id.btn_cancel );
        bt_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.techtown.oniot.FindActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_send = findViewById( R.id.btn_send );
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = find_email.getText().toString();
                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if(id.length() == 0){
                    Toast.makeText(org.techtown.oniot.FindActivity.this,"가입한 이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    find_email.requestFocus();
                    return;
                }
                if(!pattern.matcher(id).matches()){
                    Toast.makeText(org.techtown.oniot.FindActivity.this,"이메일 형식으로 입력하세요", Toast.LENGTH_SHORT).show();
                    find_email.requestFocus();
                    return;
                }

                mFirebaseAuth.sendPasswordResetEmail(id).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(org.techtown.oniot.FindActivity.this, "이메일을 전송했습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(org.techtown.oniot.FindActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(org.techtown.oniot.FindActivity.this, "가입된 이메일이 아닙니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

}