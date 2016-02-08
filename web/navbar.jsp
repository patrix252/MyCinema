
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- navbar-fixed-top --%>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp" title="MyCinema">
                    <img style= "max-width:90px; margin-top: -37px;" src="./lib/img/logo.png">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <c:if test="${!sessionScope.admin}">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="oggialcinema.jsp">Oggi al cinema</a>
                        </li>
                        <li>
                            <a href="prezzi.jsp">Prezzi</a>
                        </li>
                        <li>
                            <a href="contatti.jsp">Contatti</a>
                        </li>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <c:if test="${sessionScope.utente.getEmail()!=null}">
                            <!-- Loggato -->
                            <li>
                                <a href="registroPrenotazioni.jsp">Prenotazioni</a>
                            </li>
                            <li>
                                <a href="logout.jsp">Log Out</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.utente.getEmail()==null}">
                            <!-- Non loggato -->
                            <li> 
                                <a href="registrazione.jsp">Registrati!</a>
                            </li>



                            <li class="dropdown"> 
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Effettua l'accesso<span class="caret"></span></a>
                                <ul class="dropdown-menu" style="width: 250px">
                                    <div class="col-sm-12">

                                        <form class="form-inline" action="LoginServlet" method="POST">
                                            <div class="form-group" style="width: 100%">
                                                <input type="text" class="form-control" id="mail" name="Mail" size="10" placeholder="yourmail@sample.com" style="width: 100%">
                                            </div>
                                            <div class="form-group" style="width: 100%">
                                                <input type="password" class="form-control" id="password" name="Password" size="10" placeholder="Password" style="width: 100%">
                                            </div>
                                            <c:if test="${sessionScope.loginError}">
                                                <script src="./lib/js/openDropdown.js"></script>
                                                <p style="color: red;">Email o password non valide, reinserisci i dati!</p>
                                                <c:set var="loginError" value="false" scope="session"/>
                                            </c:if>
                                            <c:if test="${sessionScope.problemaConnessione}">
                                                <script src="./lib/js/openDropdown.js"></script>
                                                <p style="color: red;">Problema con la connessione, riprovare più tardi!</p>
                                                <c:set var="problemaConnessione" value="false" scope="session"/>
                                            </c:if>
                                            <div>
                                                <a href="passwordDimenticata.jsp">Password dimenticata?</a>
                                                <button type="submit"  class="btn btn-success pull-right">Accedi!</button>
                                            </div>
                                        </form>

                                    </div>
                                </ul>
                            </li>

                        </c:if>
                    </ul>
                </c:if>
                
                <c:if test="${sessionScope.admin}">
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="listafilm.jsp">Gestisci film</a>
                        </li>
                        <li>
                            <a href="incassiclientitop.jsp">Statistiche clienti</a>
                        </li>
                        <li>
                            <a href="cancellaprenotazioni.jsp">Cancella prenotazioni</a>
                        </li>
                        <li>
                            <a href="aggiungiSpettacolo.jsp">Aggiungi spettacolo</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <c:if test="${sessionScope.utente.getEmail()!=null}">
                            <!-- Loggato -->
                            <li>
                                <a href="logout.jsp">Log Out</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.utente.getEmail()==null}">
                            <!-- Non loggato -->
                            <li> 
                                <a href="registrazione.jsp">Registrati!</a>
                            </li>



                            <li class="dropdown"> 
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Effettua l'accesso<span class="caret"></span></a>
                                <ul class="dropdown-menu" style="width: 250px">
                                    <div class="col-sm-12">

                                        <form class="form-inline" action="LoginServlet" method="POST">
                                            <div class="form-group" style="width: 100%">
                                                <input type="text" class="form-control" id="mail" name="Mail" size="10" placeholder="yourmail@sample.com" style="width: 100%">
                                            </div>
                                            <div class="form-group" style="width: 100%">
                                                <input type="password" class="form-control" id="password" name="Password" size="10" placeholder="Password" style="width: 100%">
                                            </div>
                                            <c:if test="${sessionScope.loginError}">
                                                <script src="./lib/js/openDropdown.js"></script>
                                                <p style="color: red;">Email o password non valide, reinserisci i dati!</p>
                                                <c:set var="loginError" value="false" scope="session"/>
                                            </c:if>
                                            <c:if test="${sessionScope.problemaConnessione}">
                                                <script src="./lib/js/openDropdown.js"></script>
                                                <p style="color: red;">Problema con la connessione, riprovare più tardi!</p>
                                                <c:set var="problemaConnessione" value="false" scope="session"/>
                                            </c:if>
                                            <div>
                                                <a href="passwordimenticata.html">Password dimenticata?</a>
                                                <button type="submit"  class="btn btn-success pull-right">Accedi!</button>
                                            </div>
                                        </form>

                                    </div>
                                </ul>
                            </li>

                        </c:if>
                    </ul>
                </c:if>
            </div>
        </div>
    </nav>


