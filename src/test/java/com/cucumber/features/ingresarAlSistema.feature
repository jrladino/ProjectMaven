Feature: Ingresar al sistema con credenciales validas

  Scenario: Ingresar al sistema con usuario y contraseña validos
    Given El usuario se encuentra en la pagina de login
    When Verficar que el usuario esta en la pagina de login
    Then El usuario ingresa las credenciales validas