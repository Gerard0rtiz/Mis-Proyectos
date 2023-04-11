<?php
    session_start();
    session_destroy();
    //destrucción de la sesión, que sirve para cerrar la sesión iniciada y posteriormente se reenvia al usuario
    //NO logeado a la página de login
    header("Location:http://localhost/exemples/PHP%20Act/login.php");
?>