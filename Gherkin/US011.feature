Feature: Editar perfil de director
    Scenario: El director edita los datos de su perfil
    Given el director se encuentra en su perfil
    When seleccione la opción editar
    Then edita sus datos disponibles

    Scenario: El director guarda los cambios realizados
    Given el director realiza los cambios en su perfil
    When seleccione la opción Guardar
    Then ve el mensaje “Datos actualizados”