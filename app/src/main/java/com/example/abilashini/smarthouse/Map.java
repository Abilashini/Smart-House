package com.example.abilashini.smarthouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Abilashini on 1/3/2016.
 */
public class Map extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

    }
    public void onClickBR1(View v){
        if (v.getId() == R.id.BR1) {
            Intent i = new Intent(Map.this, RoomState.class);
            startActivity(i);
        }
    }
}
