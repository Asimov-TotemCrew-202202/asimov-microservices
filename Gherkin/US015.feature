Feature: Conocer la plataforma a través del landing page
    Scenario: El visitante es enviado al botón enlace de la plataforma
    Given el visitante visualiza “probar plataforma” en la barra de navegación
    When le da click
    Then es enviado a la sección del landing page donde podrá ver capturas de la plataforma y el botón que lo enviará a la plataforma.

    Scenario: El visitante es enviado a la plataforma
    Given el visitante esta sección “probar plataforma” del landing page
    When le da click al botón “ir a la plataforma”
    Then es enviado directamente a la plataforma.