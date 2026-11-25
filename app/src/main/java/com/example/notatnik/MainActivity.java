package com.example.notatnik;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notatka> listaNotatek;
    private ArrayAdapter<Notatka> adapter;
    private ListView listViewNotatki;
    private EditText editTytul;
    private EditText editTresc;
    private Button buttonDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotatki = findViewById(R.id.listViewNotatki);
        editTytul = findViewById(R.id.editTytul);
        editTresc = findViewById(R.id.editTresc);
        buttonDodaj = findViewById(R.id.buttonDodaj);

        listaNotatek = new ArrayList<>();
        listaNotatek.add(new Notatka("Zakupy", "Kupić mleko i chleb", false));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNotatek);
        listViewNotatki.setAdapter(adapter);

        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tytul = editTytul.getText().toString().trim();
                String tresc = editTresc.getText().toString().trim();

                if (!tytul.isEmpty()) {
                    Notatka nowaNotatka = new Notatka(tytul, tresc, false);

                    listaNotatek.add(nowaNotatka);
                    adapter.notifyDataSetChanged(); 

                    editTytul.setText("");
                    editTresc.setText("");
                }
            }
        });
    }


    public static class Notatka {
        private String tytul;
        private String tresc;
        private boolean isWazna;

        public Notatka(String tytul, String tresc, boolean isWazna) {
            this.tytul = tytul;
            this.tresc = tresc;
            this.isWazna = isWazna;
        }

        public String getTytul() { return tytul; }
        public void setTytul(String tytul) { this.tytul = tytul; }

        public String getTresc() { return tresc; }
        public void setTresc(String tresc) { this.tresc = tresc; }

        public boolean isWazna() { return isWazna; }
        public void setWazna(boolean wazna) { isWazna = wazna; }

        @Override
        public String toString() {
            return tytul + "\n" + (tresc.isEmpty() ? "" : tresc);
        }
    }
}
