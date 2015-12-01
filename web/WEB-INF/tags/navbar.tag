<%-- 
    Document   : navbar
    Created on : Oct 31, 2015, 1:57:28 PM
    Author     : Patrix
--%>


    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html" title="MyCinema">
                    <img style= "max-width:90px; margin-top: -37px;" src="./lib/img/logo.png">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left">
                    <li>
                        <a href="oggialcinema.html">Oggi al cinema</a>
                    </li>
                    <li>
                        <a href="prezzi.html">Prezzi</a>
                    </li>
                    <li>
                        <a href="contatti.html">Contatti</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <!-- REGISTRAZIONE-->
                    <li> 
                        <a href="registrazione.html">Registrati!</a>
                    </li>
                    <!-- EFFETTUA ACCESSO --> 
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Effettua l'accesso<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <div class="col-sm-12">
                                <form class="form-inline">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="mail" placeholder="yourmail@sample.com">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="password" placeholder="Password">
                                    </div>
                                    <div>
                                        <a href="passwordimenticata.html">Password dimenticata?</a>
                                        <button type="submit"  class="btn btn-success pull-right">Accedi!</button>
                                    </div>
                                </form>
                            </div>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


