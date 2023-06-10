Feature: Iniciar sesión
    Scenario: El usuario visualiza la vista Login
    Given el director se encuentra en Login de la plataforma
    When complete su usuario y contraseña
    Then visualizará las funcionalidades correspondientes al tipo de usuario.

    Scenario: Es denegado el ingreso al usuario
    Given el usuario se encuentra en Login de la platagorma
    When complete su usuario y contraseña
    Then visualizará el mensaje “Usuario o contraseña incorrecto”  