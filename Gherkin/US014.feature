Feature: Visualizar ventajas del uso de la plataforma
    Scenario: El visitante visualiza las acciones que puede realizar el docente en la plataforma
    Given el visualiza “ventajas” en la barra de navegación
    When le da click
    Then es enviado a la sección donde se visualizará en un principio las acciones que puede realizar el docente en la plataforma.

    Scenario: El visitante visualiza las acciones que puede realizar el director en la plataforma
    Given el visualiza “ventajas” en la barra de navegación
    When le da click
    Then es enviado a la sección donde se visualizará en un principio las acciones que puede realizar el director en la plataforma.