Feature: Visualizar porcentaje de progreso de un curso
    Scenario: El docente visualiza el porcentaje de ítems completados sobre un curso
    Given el docente ingresa correctamente en la plataforma
    When selecciona un curso de la lista de cursos que enseña
    Then visualizará el porcentaje de progreso de un curso, sobre los ítems completados del mismo.

    Scenario: El docente no logra visualizar el porcentaje de progreso de un curso
    Given el docente ingresa correctamente en la plataforma
    When está en el menú principal
    And pierde conexión a internet
    Then ve un mensaje de “Refresque la página actual”.