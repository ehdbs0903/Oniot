package org.techtown.oniot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText login_id, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth= FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("oniot");

        login_id = findViewById(R.id.login_id);
        login_password = findViewById(R.id.login_password);

        TextView password_finder = findViewById(R.id.password_finder);
        password_finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.techtown.oniot.LoginActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = login_id.getText().toString();
                String password = login_password.getText().toString();
                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if(id.length() == 0){
                    Toast.makeText(org.techtown.oniot.LoginActivity.this,"로그인 할 이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    login_id.requestFocus();
                    return;
                }
                if(!pattern.matcher(id).matches()){
                    Toast.makeText(org.techtown.oniot.LoginActivity.this,"이메일 형식으로 입력하세요", Toast.LENGTH_SHORT).show();
                    login_password.requestFocus();
                    return;
                }

                if(password.length() == 0){
                    Toast.makeText(org.techtown.oniot.LoginActivity.this,"로그인 할 password를 입력하세요", Toast.LENGTH_SHORT).show();
                    login_password.requestFocus();
                    return;
                }

                mFirebaseAuth.signInWithEmailAndPassword(id,password).addOnCompleteListener(org.techtown.oniot.LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(org.techtown.oniot.LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(org.techtown.oniot.LoginActivity.this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(org.techtown.oniot.LoginActivity.this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button btn_go_register = findViewById(R.id.btn_go_register);
        btn_go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.techtown.oniot.LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}