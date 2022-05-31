package com.example.gamesolitarycelta_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.Toast;

public class GameCeltaActivity extends AppCompatActivity {

    private GameCelta gcelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_celta);


        gcelta = new GameCelta();
        showTable();
        positionToken();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.item1){
            Toast.makeText(this,"opcion1", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item2){
            Toast.makeText(this,"opcion2", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.item3){
            Toast.makeText(this,"opcion3", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private final int[][] ids = {
                        {0, 0, R.id.rb02, R.id.rb03, R.id.rb04, 0, 0},
                        {0, 0, R.id.rb12, R.id.rb13, R.id.rb14, 0, 0},
            {R.id.rb20, R.id.rb21, R.id.rb22, R.id.rb23, R.id.rb24, R.id.rb25, R.id.rb26},
            {R.id.rb30, R.id.rb31, R.id.rb32, R.id.rb33, R.id.rb34, R.id.rb35, R.id.rb36},
            {R.id.rb40, R.id.rb41, R.id.rb42, R.id.rb43, R.id.rb44, R.id.rb45, R.id.rb46},
                        {0, 0, R.id.rb52, R.id.rb53, R.id.rb54, 0, 0},
                        {0, 0, R.id.rb62, R.id.rb63, R.id.rb64, 0, 0}
    };

    private void showTable(){
        RadioButton button;
        for (int f = 0; f < GameCelta.zise; f++) {
            for (int  c= 0; c < GameCelta.zise; c++)
                if (ids[f][c] != 0) {
                    button = findViewById(ids[f][c]);
                    button.setChecked(gcelta.getToken(f, c) == 1);
                }
        }
    }
    private void positionToken(){
        RadioButton rb;
        for(int f=0; f<GameCelta.zise; f++){
            for(int c=0; c<GameCelta.zise; c++){
                if(ids[f][c] !=0 ){
                    rb = findViewById(ids[f][c]);
                    int finalF = f;
                    int finalC = c;
                    rb.setOnClickListener(view ->{
                        //Toast.makeText(GameCeltaActivity.this,"ficha["+finalF+finalC+"]",Toast.LENGTH_SHORT).show();
                        gcelta.play(finalF,finalC);
                        showTable();
                        if (gcelta.gameOver()) {
                            Toast.makeText(GameCeltaActivity.this,"Juego terminado",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    }



}