Feature: Listar los docentes
    Scenario: Existen docentes registrado
    Given el director ingresa a la plataforma y está en la vista principal
    When seleccione ver listado de docentes
    Then podrá visualizar un card con información personal por cada docente

    Scenario: No hay docentes registrados
    Given el director ingresa a la plataforma y está en la vista principal
    When seleccione ver listado de docentes
    Then ve un mensaje de “Registre docentes”