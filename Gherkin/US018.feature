Feature: Editar curso
    Scenario: Editar descripción curso
    Given existe una entidad “Curso”
    When se modifica los campos deseados un curso
    Then la organización actualizará los detalles dicho curso en la tabla “Curso”