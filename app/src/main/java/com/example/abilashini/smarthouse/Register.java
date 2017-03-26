package com.example.abilashini.smarthouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Abilashini on 1/3/2016.
 */
public class Register extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }
    /*
    public void onRegisterClick(View v){
        if(v.getId()==R.id.BRegister){
            EditText userName =(EditText)findViewById(R.id.ETUsername);
            EditText pass1 =(EditText)findViewById(R.id.ETpass1);
            EditText pass2 =(EditText)findViewById(R.id.ETpass2);

            String userNamestr = userName.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str)){
                Toast pass = Toast.makeText(Register.this,"Passwords do not match", Toast.LENGTH_SHORT);
                pass.show();
            }
            else{
                User u = new User();
                u.setUserName(userNamestr);
                u.setPassword(pass1str);

                helper.insertUser(u);
            }
        }
    }
    */
}
