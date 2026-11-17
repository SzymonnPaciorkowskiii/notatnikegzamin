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

        // Przypisanie widoków z layoutu
        listViewNotatki = findViewById(R.id.listViewNotatki);
        editTytul = findViewById(R.id.editTytul);
        editTresc = findViewById(R.id.editTresc);
        buttonDodaj = findViewById(R.id.buttonDodaj);

        // Inicjalizacja listy i dodanie przykładowej notatki
        listaNotatek = new ArrayList<>();
        listaNotatek.add(new Notatka("Zakupy", "Kupić mleko i chleb", false));

        // Konfiguracja adaptera
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNotatek);
        listViewNotatki.setAdapter(adapter);

        // Obsługa kliknięcia przycisku dodawania
        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tytul = editTytul.getText().toString().trim();
                String tresc = editTresc.getText().toString().trim();

                // Sprawdzamy, czy chociaż tytuł nie jest pusty
                if (!tytul.isEmpty()) {
                    Notatka nowaNotatka = new Notatka(tytul, tresc, false);

                    listaNotatek.add(nowaNotatka);
                    adapter.notifyDataSetChanged(); // Odświeżenie widoku listy

                    // Wyczyszczenie pól tekstowych po dodaniu
                    editTytul.setText("");
                    editTresc.setText("");
                }
            }
        });
    }

    /**
     * Klasa reprezentująca pojedynczą notatkę użytkownika.
     */
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
            // Ładne wyświetlanie w elemencie listy ListView
            return tytul + "\n" + (tresc.isEmpty() ? "" : tresc);
        }
    }
}
