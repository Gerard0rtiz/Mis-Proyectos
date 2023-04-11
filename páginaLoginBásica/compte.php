<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Act3-M09.3</title>
</head>
<body>
    <?php
        require("protection.php");
        //requiere entrar a protection.php antes de continuar, para así revisar que se ha logeado correctamente con un
        //usuario válido
    ?>
<h1>Menú navegació</h1><br>
    <a href="login.php">Pàgina "login.php"</a><br><!--acceso a pestaña de login-->
    <a href="compte.php">Pàgina "compte.php"</a><br><!--acceso a pestaña de compte-->
    <h1>L'usuari <?php echo $_SESSION["user_logged"];?> ha iniciat sessió</h1><br><!--mensaje con el usuario logeado-->
    <a href="logout.php">Tancar sessió</a><!--cierre de sesión con logout.php-->
</body>
</html>