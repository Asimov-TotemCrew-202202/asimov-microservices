Feature: Registrar usuario de director
    Scenario: El director logra registrarse en la plataforma
    Given el director se encuentra en el inicio de sesión de la plataforma
    When completa los campos requeridos
    And seleccione la opción “Registrarse”
    Then ve un mensaje de “Usuario registrado correctamente!”

    Scenario: El director no completa los datos requeridos.
    Given el director se encuentra en el formulario de registro
    When le falte completar algún campo
    And presiona la opción “Registrarse”
    Then aparece el mensaje “Complete todos los datos requeridos”