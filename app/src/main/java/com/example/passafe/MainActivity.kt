package com.example.passafe

import android.content.ClipData // Per creare un oggetto da copiare negli appunti
import android.content.ClipboardManager // Per accedere al sistema copia/incolla
import android.content.Context // Fornisce accesso a servizi di sistema
import android.os.Bundle // Per passare dati tra attività e gestire lo stato
import android.widget.Button // Widget per i bottoni
import android.widget.EditText // Campo di input testuale
import android.widget.TextView // Per visualizzare testi statici
import android.widget.Toast // Per mostrare messaggi brevi all'utente
import androidx.appcompat.app.AppCompatActivity // Base per creare un Activity

class MainActivity : AppCompatActivity() {

// HashMap per associare un nome utente ad una password -->
    private val mappaUtenti = HashMap<String, String>()
    private var totaleSalvate = 0

 // Metodo chiamato alla creazione dell'Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Collegamento dei widget dal layout XML al codice
        val campoNome = findViewById<EditText>(R.id.editTextNomeUtente)
        val bottoneGenera = findViewById<Button>(R.id.btnGenera)
        val testoPassword = findViewById<TextView>(R.id.textPassword)
        val bottoneCopia = findViewById<Button>(R.id.btnCopia)
        val messaggioTextView = findViewById<TextView>(R.id.textMessaggio)
        val totaleTextView = findViewById<TextView>(R.id.textTotale)

// Listener per il bottone "Genera Password"
        bottoneGenera.setOnClickListener {
            val nomeUtente = campoNome.text.toString()

            if (nomeUtente.isNotEmpty()) {
                val passwordSalvata = mappaUtenti[nomeUtente]

                val password = if (passwordSalvata != null) {
                    passwordSalvata
                } else {
                    val nuovaPassword = generaPassword()
                    mappaUtenti[nomeUtente] = nuovaPassword
                    totaleSalvate += 1
                    totaleTextView.text = "Totale password salvate: $totaleSalvate"
                    nuovaPassword
                }

// Mostra la password generata/recuperata e aggiorna il messaggio
                testoPassword.text = password
                messaggioTextView.text = "Password per $nomeUtente:"
            } else {
                Toast.makeText(this, "Inserisci un nome valido", Toast.LENGTH_SHORT).show()
            }
        }

// Listener per il bottone "Copia"
        bottoneCopia.setOnClickListener {
            val passwordDaCopiare = testoPassword.text.toString()
            if (passwordDaCopiare.isNotEmpty()) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("password", passwordDaCopiare)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Password copiata negli appunti" , Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nessuna password da copiare", Toast.LENGTH_SHORT).show()
            }
        }
    }

// Funzione per generare una password casuale
    private fun generaPassword(): String {
        val caratteri = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#\$%^&*"
        var password = ""
        for (i in 1..12) {
            password += caratteri.random()
        }
        return password
    }
}