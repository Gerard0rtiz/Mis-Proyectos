<?php
session_start();//inicia sesión
//si el usuario no existe o no está logeado, por más que se pulse el enlace de la pestaña login para acceder a 
//compte.php, no podrá ya que protection.php reenviará al usuario no logeado a la página de login
if (!array_key_exists("user_logged", $_SESSION)){
    header("Location:http://localhost/exemples/PHP%20Act/login.php");
}

?>