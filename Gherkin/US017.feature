Feature: Editar perfil de docente
    Scenario: El docente edita los datos de su perfil
    Given el docente se encuentra en su perfil
    When seleccione la opción editar
    Then edita sus datos disponibles

    Scenario: El docente guarda los cambios realizados
    Given el docente realiza los cambios en su perfil
    When seleccione la opción Guardar
    Then ve el mensaje “Datos actualizados”