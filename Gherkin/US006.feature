Feature: Publicar comunicado
    Scenario: El director crea un comunicado por primera vez
    Given el director se encuentra en la vista “Comunicados”
    When completa el campo mensaje y selecciona “crear comunicado”
    Then los docentes reciben el comunicado en la vista “Mis Comunicados”

    Scenario: El director crear un comunicado por más de una vez
    Given el director se encuentra en la vista “Comunicados”
    When completa el campo mensaje y selecciona “crear comunicado”
    Then el comunicado se añade a la lista de comunicados enviados debajo del campo de creación.