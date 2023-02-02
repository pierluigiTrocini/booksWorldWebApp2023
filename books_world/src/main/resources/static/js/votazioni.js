window.addEventListener('load', function(e) {

    var recensioni = document.querySelectorAll(".recensione");

    recensioni.forEach( recensione => {

        var idRecensione = recensione.getAttribute("value");
        var buttonAFavore = document.getElementById("votaAFavore_"+idRecensione);
        var buttonContro = document.getElementById("votaContro_"+idRecensione);

        buttonAFavore.addEventListener("click", function(e){
            e.preventDefault();
            $.ajax({
                type: "post",
                url: "/votaAFavore",
                data: {"idRecensione": idRecensione},
                success: function(risposta) {
                    if (risposta === true) {
                        document.getElementById(idRecensione).parentNode.remove();
                    }
                }
            });
        });

        buttonContro.addEventListener("click", function(e){
            $.ajax({
                type: "post",
                url: "/votaContro",
                data: {"idRecensione": idRecensione},
                success: function(risposta) {
                    if (risposta === true) {
                        document.getElementById(idRecensione).parentNode.remove();
                    }
                }
            });
        });

    });

});
