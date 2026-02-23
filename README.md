<h1>Comunicazione TCP</h1>
<h2>Descrizione</h2>
<p>
  Il programma rappresenta la comunicazione Client-Server usando il protocollo TCP. <br>
  Di base, quindi, avremo due attori: il Client e il Server. <br>
  Il Server si mette in ascolto su una porta stabilita e rimane in attesa di una richiesta da parte dei client. <br>
  Il client a sua volta può connettersi al server e scambiare messaggi.
</p>

<hr>

<h2>Attori</h2>
<p>
  Abbiamo detto quindi che il programma prevede due attori:
  <ol>
    <li><b>CLIENT</b></li>
    <li><b>SERVER</b></li>
  </ol>
  Il <b>CLIENT</b> può connettersi al server, scrivere e leggere messaggi e chiudere la connessione. <br>
  Il <b>SERVER</b>, quando verrà avviato, si troverà in stato di ascolto finché un client non effettua una richiesta di connessione. A quel punto il server può leggere e scrivere messaggi, terminare la connessione e "spegnersi".
</p>

<hr>

<h2>Come si usa</h2>
<p>
  Il funzionamento è semplice: avviamo il mainServer (che istanzia un oggetto di classe Server e lo avvia) e successivamente il mainClient (stessa cosa del mainServer ma con il client). <br>
  Una volta che entrambi sono attivi e connessi l'uno con l'altro, è possibile scrivere messaggi sulla console lato client. Questi messaggi verranno letti dal server e verranno restituiti indietro da esso come "risposta".
</p>
