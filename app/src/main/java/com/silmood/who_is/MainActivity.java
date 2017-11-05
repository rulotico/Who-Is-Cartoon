package com.silmood.who_is;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Modelo
    Character[] characters = new Character[] {
            new Character(R.string.courage, R.drawable.courage),
            new Character(R.string.dexter, R.drawable.dexter),
            new Character(R.string.jack, R.drawable.jack)
    };

    //Vistas
    ImageView imgCharacter;
    TextView labelName;
    TextView labelScore;

    //Variables de control
    int nameIndex;
    int imageIndex;
    int score = 0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciando los elementos de la UI
        initView();

        //Asignar valores en UI
        updateCharacter();
    }

    private View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commitAnswer(view);
            }
        };
    }

    private void initView() {
        referenceView();
        labelScore.setText(String.format(Locale.US,"%d", score));

        View.OnClickListener listener = getListener();

        //Creación de Listener
        getButton(R.id.btnTrue).setOnClickListener(listener);
        getButton(R.id.btnFalse).setOnClickListener(listener);
    }

    public void onNext(View btnNext) {
        updateCharacter();
    }

    private Button getButton(int id) {
        return (Button) findViewById(id);
    }

    private void referenceView() {
        imgCharacter = (ImageView) findViewById(R.id.imgCharacter);
        labelName = (TextView) findViewById(R.id.labelName);
        labelScore = (TextView) findViewById(R.id.labelScore);
    }

    private void commitAnswer(View view) {
        boolean respuesta = nameIndex == imageIndex;
        boolean button = view.getId() == R.id.btnTrue;

        evaluateResponse(respuesta, button);
        updateCharacter();
    }

    private void updateCharacter() {
        //Actualizar las variables de controlor
        nameIndex = random.nextInt(characters.length);
        imageIndex = random.nextInt(characters.length);

        //Obtener nuevos personajes aleatorios
        Character characterName = characters[nameIndex];
        Character characterImage = characters[imageIndex];

        //Mostrar información de nuevos personajes
        labelName.setText(characterName.nameResId);
        imgCharacter.setImageResource(characterImage.imageResId);
    }

    private void evaluateResponse(boolean correctAnswer, boolean userAnswer) {
        if (correctAnswer == userAnswer) {
            score += 1;
            Toast.makeText(this, "Correcto :)", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Incorrecto :(", Toast.LENGTH_LONG).show();
        }

        labelScore.setText(String.format(Locale.US,"%d", score));
    }


}
