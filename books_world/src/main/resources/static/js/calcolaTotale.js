window.addEventListener('load', function(e) {
    var totale = 0;
    var listaPrezzi = document.querySelectorAll("#prezzo");
    listaPrezzi.forEach(function(prezzo) {
        totale += prezzo;
    });
    this.document.querySelector("#totale").innerHTML += totale + "€";

    var libri = this.document.querySelectorAll(".libro");

    libri.forEach(function(libro) {
        var isbnLibro = libro.getAttribute("value");
        var idAumenta = "aumentaQuantita_" + isbnLibro;
        var idDiminuisci = "diminuisciQuantita_" + isbnLibro;

        var butAumenta = document.getElementById(idAumenta);
        var butRimuovi = document.getElementById(idDiminuisci);

        butAumenta.addEventListener("click", function(event){
            $.ajax({
                type: "post",
                url: "/aumentaQuantita",
                data: {"isbn": isbnLibro},
                success: function(risposta) {
                    document.getElementById("quantita_"+isbnLibro).innerHTML = risposta;
                }
            });
        });

        butRimuovi.addEventListener("click", function(event){
            $.ajax({
                type: "post",
                url: "/diminuisciQuantita",
                data: {"isbn": isbnLibro},
                success: function(risposta) {
                    document.getElementById("quantita_"+isbnLibro).innerHTML = risposta;
                }
            });
        });

    });

});


