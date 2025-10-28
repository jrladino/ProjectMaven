Feature: Crear empleado en el sistema PIM

  Scenario: Crear un nuevo empleado con datos validos
    Given El usuario se encuentra en la pagina de login
    When Verficar que el usuario esta en la pagina de login
    Then El usuario ingresa las credenciales validas
    And El usuario se encuentra en la pagina de inicio del sistema PIM
    When El usuario ingresa al modulo PIM
    Then Usuario hace clic en el boton Agregar
    Then El usuario crea un nuevo empleado con los datos validos
    And El usuario guarda el formulario con los datos