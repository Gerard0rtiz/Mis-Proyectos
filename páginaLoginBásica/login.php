<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Act3-M09.3</title>
</head>
<body>
    <h1>Menú navegació</h1><br>
    <a href="login.php">Pàgina "login.php"</a><br><!--acceso a pestaña de login-->
    <a href="compte.php">Pàgina "compte.php" (Només es pot accedir si has iniciat sessió correctament)</a><br><!--acceso a pestaña de cuenta en caso de que se haya iniciado sesión anteriormente-->
    <h1>LOGIN</h1><br>
    <form method="post" action="session_authentication.php"><!--formulario para iniciar sesión-->
    Usuari: <input name="user" type="text"/><br>
    Contrasenya: <input name="pswd" type="password"/><br>
    <button name="submit" type="submit">Accedir</button>
    </form>
</body>
</html>