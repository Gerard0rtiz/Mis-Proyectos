<?php
    $usuaris = array();//creamos array usuarios y creamos varios usuarios con sus contraseñas
    $usuaris["admin"]="1234";
    $usuaris["gerard"]="ortiz";
    if(isset($_POST["submit"])){//en el caso de que el boton enviar se ejecute entrará
        
        if(isset($_POST["user"]) && isset($_POST["pswd"])){//en caso de que haya un usuario y una contraseña lo guarda en variables
            $user = $_POST["user"];
            $pswd = $_POST["pswd"];
            
            if(isset ($usuaris[$user])){ //si el usuario introducido está en el array entrará, sino, te devuelve a la página de login
                if($usuaris[$user] == $pswd){ //si el usuario coincide, revisa si la contraseña es correcta, sino, te devuelve a login
                    session_start();//inicia sesión
                    $_SESSION['user_logged']=$user;//guarda el usuario en el array de $_SESSION
                    header("Location:http://localhost/exemples/PHP%20Act/compte.php");//reenvia a página "compte.php"
                } else{
                header("Location:http://localhost/exemples/PHP%20Act/login.php");
                }
            } else {
                header("Location:http://localhost/exemples/PHP%20Act/login.php");
            }
        }else{
            header("Location:http://localhost/exemples/PHP%20Act/login.php");
        }
    }
?>