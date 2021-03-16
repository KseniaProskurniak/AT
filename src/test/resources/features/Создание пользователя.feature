#language: ru

Функция: Вход в систему в качестве администратора

  Предыстория:
    Пусть существует пользователь "user1" с параметрами:
      | login    | admin    |
      | password | admin123 |
      | admin    | true     |
      | status   | 1        |
    И открыт браузер на главной странице

  @feature
  Сценарий: Создание пользователя с генерацией пароля
    Если авторизоваться пользователем "user1"
    И нажать кнопку "Администрирование" в меню "Заголовок"
    И нажать кнопку "Пользователи" в меню "Администрирование пользователей"
    И нажать кнопку "Новый пользователь" в меню "Администрирование пользователей"
    И ввести в поле "Пользователь" случайное значение
    И ввести в поле "Имя" случайное значение
    И ввести в поле "Фамилия" случайное значение
    И ввести в поле "Email" случайное значение
    И нажать чекбокс "Создание пароля"
    И нажать кнопку "Создать" в меню "Новый пользователь"
    И проверяет корректность создания


  @feature
  Сценарий: Создание пользователя с заполнением и подтверждением пароля
    Если авторизоваться пользователем "user1"
    И нажать кнопку "Администрирование" в меню "Заголовок"
    И нажать кнопку "Пользователи" в меню "Администрирование пользователей"
    И нажать кнопку "Новый пользователь" в меню "Администрирование пользователей"
    И ввести в поле "Пользователь" случайное значение
    И ввести в поле "Имя" случайное значение
    И ввести в поле "Фамилия" случайное значение
    И ввести в поле "Email" случайное значение
    И заполнить и подтвердить пароль
    И нажать кнопку "Создать" в меню "Новый пользователь"
    И проверяет корректность создания
