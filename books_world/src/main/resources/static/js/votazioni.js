window.addEventListener('load', function(e) {

    var segnalazioni = document.querySelectorAll(".segnalazione");

    segnalazioni.forEach( segnalazione => {

        var idSegnalazione = segnalazione.getAttribute("value");
        var buttonAFavore = document.getElementById("votaAFavore"+idSegnalazione);
        var buttonContro = document.getElementById("votaContro"+idSegnalazione);

        buttonAFavore.addEventListener("click", function(e){
            $.ajax({
                type: "post",
                url: "/votaAFavore",
                data: {"idSegnalazione": idSegnalazione},
                success: function(risposta) {
                    document.getElementById("")
                }
            });
        });

        buttonContro.addEventListener("click", function(e){
            $.ajax({
                type: "post",
                url: "/votaContro",
                data: {"idSegnalazione": idSegnalazione},
                success: function(risposta) {
                    alert("La votazione è andata a buon fine");
                }
            });
        });

    });

});
