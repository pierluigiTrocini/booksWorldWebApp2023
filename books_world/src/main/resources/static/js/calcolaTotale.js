window.addEventListener('load', function(e) {

    calcolaTotale();

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
                    var quantita = parseInt(risposta);
                    var prezzo = parseFloat(document.getElementById("prezzo_"+isbnLibro).innerHTML);
                    document.getElementById("prezzo_"+isbnLibro).innerHTML =
                                parseFloat((prezzo/(quantita-1))*quantita).toFixed(2)+"€";
                    calcolaTotale();
                }
            });
        });

        butRimuovi.addEventListener("click", function(event){
            $.ajax({
                type: "post",
                url: "/diminuisciQuantita",
                data: {"isbn": isbnLibro},
                success: function(risposta) {
                    if (risposta >= 1) {
                        document.getElementById("quantita_"+isbnLibro).innerHTML = risposta;
                        var quantita = parseInt(risposta);
                        var prezzo = parseFloat(document.getElementById("prezzo_"+isbnLibro).innerHTML);
                        document.getElementById("prezzo_"+isbnLibro).innerHTML =
                                    parseFloat((prezzo/(quantita+1))*quantita).toFixed(2)+"€";
                        calcolaTotale();
                    }
                    else {
                        document.getElementById("libro_"+isbnLibro).parentNode.remove();
                        calcolaTotale();
                    }
                }
            });
        });

    });

});


function calcolaTotale() {
    var libri = this.document.querySelectorAll(".libro");
    var totale = 0;
    libri.forEach( libro => {
        var isbn = libro.getAttribute("value");
        totale += parseFloat(document.getElementById("prezzo_"+isbn).innerHTML.valueOf());
    });
    this.document.querySelector("#totale").innerHTML = "Totale: " + totale.toFixed(2) + "€";
}
