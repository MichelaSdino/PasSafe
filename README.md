README – PasSafe
Autori: Otello Panaro, Michela Sdino
Corso: Programmazione di Dispositivi Mobili
Progetto: App Android – Generatore di password sicure
 
Obiettivo
L'app è stata progettata per aiutare persone meno esperte a generare e gestire password sicure, attraverso un'interfaccia semplice ed efficace.
È utile soprattutto per chi desidera migliorare la propria sicurezza digitale senza dover utilizzare strumenti avanzati o complessi.
Funzionalità principali
Inserimento di un nome utente (es. “Netflix”, “Gmail”, “Università”)
Pulsante “Genera password” → crea una password casuale con lettere, numeri e simboli
Visualizzazione immediata della password generata
Messaggi chiari per guidare l’utente (tramite Toast)
Contatore delle password generate correttamente
Funzionalità originale
L’app mantiene una sola password per ogni nome utente, finché l’app resta aperta. Questo avviene senza salvataggi permanenti, ma solo con una HashMap temporanea in memoria.
Se si inserisce un nome nuovo → viene generata una nuova password
Se si reinserisce lo stesso nome → viene mostrata la stessa password associata
Questa gestione è leggera, immediata, e perfettamente in linea con le basi di Kotlin e Android.
Struttura e funzionamento
activity_main.xml
Layout verticale (LinearLayout) che contiene:
EditText per inserire il nome
Button per generare la password
TextView per mostrare la password
TextView per il messaggio dinamico
TextView per il contatore
MainActivity.kt
Contiene la logica dell’app:
Generazione della password tramite funzione Kotlin
Associazione nome/password con HashMap
Gestione dei messaggi all’utente con Toast.makeText(...)
Controllo dei nomi già usati tramite containsKey() e accesso diretto alla mappa
 
