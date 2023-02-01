window.addEventListener("load", function(){
    var submit = this.document.querySelector(".btn.btn-primary");

    submit.addEventListener("click", (event) => {
        event.preventDefault();

        var utente = {
            "nome": document.getElementById("name").value,
            "cognome": document.getElementById("surname").value,
            "data_di_nascita": document.getElementById("date").value,
            "username": document.getElementById("username").value,
            "email": document.getElementById("email").value,
            "password": document.getElementById("pwd").value,
            "moderatore": false
        };

        $.ajax({
            type:"POST",
            url: "/confirmRegistration",
            data: JSON.stringify(utente),
            contentType: "application/json; charset=utf-8",
            dataType: 'text',
            success: function(response){
                switch (response) {
                    case "USED_EMAIL":
                        alert("indirizzo email già utilizzato");
                        document.getElementById("email").classList.add("is-invalid");
                        break;
                    case "USED_USERNAME":
                        alert("username già utilizzato");
                        document.getElementById("email").classList.add("is-invalid");
                        break;
                    case "SERVICE_UNAVAILABLE":
                        alert("Servizio al momento non disponibile");
                        break;
                    case "USER_SAVED":
                        alert("Registrato con successo");
                        window.location.href = "login.html";
                        break;

                    default:
                        break;
                }

            }
        })
    })
})

// function writeNext(i)
// {
//     document.write(i);

//     if(i == 5)
//         return;

//     setTimeout(function()
//     {v
//         writeNext(i + 1);

//     }, 2000);
// }
