function pwdStrength(){
    let password = document.getElementById("pwd");
    let strengthBanner = document.getElementById("strengthLine");

    let strongPassword = new RegExp('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})');
    let mediumPassword = new RegExp('((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{6,}))|((?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=.{8,}))');

    if(strongPassword.test(password.value))
        strengthBanner.style.borderColor = 'green';
    else if (mediumPassword.test(password.value))
        strengthBanner.style.borderColor = 'orange';
    else
        strengthBanner.style.borderColor = "red";
    
}

function showPassword(){
    let pwd = document.getElementById("pwd");
    if(pwd.type === 'password')
        pwd.type = 'text';
    else
        pwd.type = 'password';
}