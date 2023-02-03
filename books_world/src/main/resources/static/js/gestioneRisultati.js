this.document.getElementById("ordineAlfabeticoAZ").addEventListener("click", (event) => {
    event.preventDefault();

    $.ajax({
        type: "post",
        url: "/ordineAlfabetico",
        contentType: "application/json; charset=utf-8",
        data: {
            content: document.getElementById("searchContent").value,
            value: true
        },
        success: function(response){

        }
    })
    
})
this.document.getElementById("ordineAlfabeticoZA").addEventListener("click", (event) => {
    event.preventDefault();

    $.ajax({
        type: "post",
        url: "/ordineAlfabetico",
        contentType: "application/json; charset=utf-8",
        data: {
            content: document.getElementById("searchContent").value,
            value: null
        },
        success: function(response){

        }
    })
    
})
this.document.getElementById("votatiGoogle").addEventListener("click", (event) => {
    event.preventDefault();

    $.ajax({
        type: "post",
        url: "/googleRating",
        contentType: "application/json; charset=utf-8",
        data: {
            content: document.getElementById("searchContent").value,
            value: true
        },
        success: function(response){

        }
    })
    
})
this.document.getElementById("senzaVoto").addEventListener("click", (event) => {
    event.preventDefault();

    $.ajax({
        type: "post",
        url: "/googleRating",
        contentType: "application/json; charset=utf-8",
        data: {
            content: document.getElementById("searchContent").value,
            value: true
        },
        success: function(response){

        }
    })
    
})
this.document.getElementById("language").addEventListener("click", (event) => {
    event.preventDefault();

    $.ajax({
        type: "post",
        url: "/filtroLingua",
        contentType: "application/json; charset=utf-8",
        data: {
            content: document.getElementById("searchContent").value,
            value: navigator.language.split("-")[0]
        },
        success: function(response){

        }
    })
    
})