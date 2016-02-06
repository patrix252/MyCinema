function showSpettacoli(str) {
    $("#first_film").hide();
    $("#cancella").hide();
    getSpettacoli(str);
    getIncassoFilm(str);
}
function getIncassoFilm(str){
    var xhttp; 
    if (str == "") {
        document.getElementById("films").innerHTML = "";
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("incasso_film").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "IncassiServlet?t="+str, true);
    xhttp.send();
}
function getSpettacoli(str){
    $("#incasso_spettacolo").hide();
    var xhttp; 
    if (str == "") {
        document.getElementById("films").innerHTML = "";
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("spettacoli").innerHTML = xhttp.responseText;
            $("#spettacoli").show();
        }
    };
    xhttp.open("GET", "IncassiServlet?q="+str, true);
    xhttp.send();
}
function getIncassoSpettacolo(str){
    $("#cancella").show();
    $("#first_spettacoli").hide();
    $("#incasso_spettacolo").show();
    var xhttp; 
    if (str == "") {
        document.getElementById("spettacoli").innerHTML = "";
        return;
    }
    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById("incasso_spettacolo").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "IncassiServlet?s="+str, true);
    xhttp.send();
}