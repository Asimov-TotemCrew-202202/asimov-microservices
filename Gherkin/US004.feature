Feature: Registrar planilla de docentes
    Scenario: El director registra a los docentes de su institución educativa
    Given el docente se encuentra en la vista Listar docentes
    When selecciona el botón de registrar nuevos docentes
    Then ingresara una lista de nuevos docentes que se crearán y añadirán a su institución

    Scenario: El docente ya se encuentra registrado.
    Given se encuentra en la vista de ingresar nuevos docentes
    When el director ingresa una o más credenciales de docentes existentes en la base de datos
    Then ve un mensaje de “Uno o más credenciales existentes”.