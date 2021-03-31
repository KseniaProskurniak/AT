#language: ru

Функция: Авторизация неподтвержденным пользователем

  Предыстория:
    Пусть существует пользователь "user1" с параметрами:
      | admin    | false    |
      | status   | 2        |
    И открыт браузер на главной странице

  @feature
  Сценарий: Вход в систему в качестве неподтвержденного пользователя
    Если авторизоваться пользователем "user1"
    То на главной странице отображается поле "Домашняя страница"
    И на странице "Вход в систему" не отображается элемент "Моя страница"
    И на странице "Вход в систему" отображается поле "Войти"
    И на странице "Вход в систему" отображается поле "Регистрация"
    И отображается ошибка с текстом "Ваша учётная запись создана и ожидает подтверждения администратора."