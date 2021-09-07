function checkLogin(formName) {
    let login = document.getElementById(formName).querySelector('[name="login"]');
    return login.value !== '';
}

function checkPassword(formName) {
    let password = document.getElementById(formName).querySelector('[name="password"]');
    return password.value !== '';
}