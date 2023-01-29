window.addEventListener('load', function(e) {
    var totale = 0;
    var listaPrezzi = document.querySelectorAll("#prezzo");
    listaPrezzi.forEach(function(prezzo) {
        totale += prezzo;
    });
    this.document.querySelector("#totale").innerHTML += totale + "€";
});
