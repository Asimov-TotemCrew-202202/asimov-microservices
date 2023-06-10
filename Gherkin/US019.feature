Feature: Eliminar Curso
    Scenario: Eliminar curso
    Given existe una entidad “Curso”
    When selecciona eliminar un curso en particular
    Then la organización eliminará dicho curso de la tabla “Curso”