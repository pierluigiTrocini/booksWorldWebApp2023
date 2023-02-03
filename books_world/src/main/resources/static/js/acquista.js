window.addEventListener("load", function() {
    var butAcquista = document.querySelector("#butAcquista");


    butAcquista.addEventListener("click", function(e) {
        var canSubmit = false;
        var txtNome = document.getElementById("fname").value;
        var txtEmail = document.getElementById("email").value;
        var txtAdr = document.getElementById("adr").value;
        var txtCity = document.getElementById("city").value;
        var txtState = document.getElementById("state").value;
        var txtCountry = document.getElementById("country").value;
        var txtCap = document.getElementById("cap").value;
        var txtCname = document.getElementById("cname").value;
        var txtCcnum = document.getElementById("ccnum").value;
        var txtScadenza = document.getElementById("scadenza").value;
        var txtCvv = document.getElementById("cvv").value;

        if (txtNome === "" || txtEmail === "" || txtAdr === "" || txtCity === "" || txtState === "" || txtCountry === ""
                    || txtCap === "" || txtCname === "" || txtCcnum === "" || txtScadenza === "" || txtCvv === "")
            canSubmit = false;

        if (canSubmit)
            document.querySelector("#form").submit();
        else
            alert("Assicurati che tutti i campi siano stati compilati");

    });

});
