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
  Сценарий: Вход в систему в качестве администратора системы
    Если авторизоваться пользователем "user1"
    То на главной странице отображается поле "Домашняя страница"
    То на главной странице отображается поле "Моя страница"
    То на главной странице отображается поле "Проекты"
    То на главной странице отображается поле "Администрирование"
    То на главной странице отображается поле "Помощь"
    То на главной странице отображается поле "Моя учётная запись"
    То на главной странице отображается поле "Выйти"