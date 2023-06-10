Feature: Visualizar competencias de un curso
    Scenario: El docente visualiza las competencias asignadas a un curso
    Given el docente se encuentra en la vista “Mis Cursos”
    When selecciona un curso en particular
    Then ve en la sección de la derecha, las competencias correspondientes a dicho curso.

    Scenario: El docente no visualiza las competencias
    Given el docente se encuentra en la vista “Mis Cursos”
    When selecciona un curso en particular
    And el curso no tiene asignados competencias
    Then ve un mensaje “Aún no hay competencias asignadas al curso”.