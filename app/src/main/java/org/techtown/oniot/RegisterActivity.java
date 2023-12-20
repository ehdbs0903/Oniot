package org.techtown.oniot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText register_name, register_id, register_password, register_organize;
    private Button btn_register, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth= FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("oniot");

        register_name = findViewById(R.id.register_name);
        register_id = findViewById(R.id.register_id);
        register_password = findViewById(R.id.register_password);
        register_organize = findViewById(R.id.register_organize);

        btn_cancel = findViewById( R.id.btn_cancel );
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(org.techtown.oniot.RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = register_id.getText().toString();
                String password = register_password.getText().toString();
                String name = register_name.getText().toString();
                String organize = register_organize.getText().toString();

                if(name.length() == 0){
                    Toast.makeText(org.techtown.oniot.RegisterActivity.this,"가입 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                    register_name.requestFocus();
                    return;
                }

                if(id.length() == 0){
                    Toast.makeText(org.techtown.oniot.RegisterActivity.this,"가입 Email을 입력하세요", Toast.LENGTH_SHORT).show();
                    register_id.requestFocus();
                    return;
                }

                if(password.length() == 0){
                    Toast.makeText(org.techtown.oniot.RegisterActivity.this,"가입 password를 입력하세요", Toast.LENGTH_SHORT).show();
                    register_password.requestFocus();
                    return;
                }

                if(organize.length() == 0){
                    Toast.makeText(org.techtown.oniot.RegisterActivity.this,"가입 소속을 입력하세요", Toast.LENGTH_SHORT).show();
                    register_organize.requestFocus();
                    return;
                }

                mFirebaseAuth.createUserWithEmailAndPassword(id, password).addOnCompleteListener(org.techtown.oniot.RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setId(firebaseUser.getEmail());
                            account.setPassword(password);
                            account.setOrganize(organize);
                            account.setName(name);


                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(org.techtown.oniot.RegisterActivity.this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(org.techtown.oniot.RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(org.techtown.oniot.RegisterActivity.this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}