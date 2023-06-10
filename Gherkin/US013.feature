Feature: Visualizar características por cada tipo de usuario de la plataforma
    Scenario: El visitante visualiza rápidamente del tipo usuario docente
    Given el visitante ingresa al landing page de la plataforma
    When le da click a “usuario docente” en la barra de navegación que está en la parte superior del sitio web
    Then es dirigido rápidamente a la sección con información sobre el tipo de usuario docente.

    Scenario: El visitante visualiza rápidamente del tipo usuario director
    Given el visitante ingresa al landing page de la plataforma
    When le da click a “usuario director” en la barra de navegación que está en la parte superior del sitio web
    Then es dirigido rápidamente a la sección con información sobre el tipo de usuario director.