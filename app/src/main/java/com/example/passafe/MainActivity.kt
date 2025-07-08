package com.example.passafe

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val mappaUtenti = HashMap<String, String>()
    private var totaleSalvate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val campoNome = findViewById<EditText>(R.id.editTextNomeUtente)
        val bottoneGenera = findViewById<Button>(R.id.btnGenera)
        val testoPassword = findViewById<TextView>(R.id.textPassword)
        val bottoneCopia = findViewById<Button>(R.id.btnCopia)
        val messaggioTextView = findViewById<TextView>(R.id.textMessaggio)
        val totaleTextView = findViewById<TextView>(R.id.textTotale)

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

                testoPassword.text = password
                messaggioTextView.text = "Password per $nomeUtente:"
            } else {
                Toast.makeText(this, "Inserisci un nome valido", Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun generaPassword(): String {
        val caratteri = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#\$%^&*"
        var password = ""
        for (i in 1..12) {
            password += caratteri.random()
        }
        return password
    }
}