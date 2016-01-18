function showSpettacoli(str) {
                $("#incasso").hide();
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
            function getIncasso(str){
                $("#incasso").show();
                var xhttp; 
                if (str == "") {
                  document.getElementById("spettacoli").innerHTML = "";
                  return;
                }
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                  if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("incasso").innerHTML = xhttp.responseText;
                  }
                };
                xhttp.open("GET", "IncassiServlet?s="+str, true);
                xhttp.send();
            }