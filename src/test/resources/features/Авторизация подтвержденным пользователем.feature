#language: ru

Функция: Авторизация подтвержденным пользователем

  Предыстория:

  @feature
  Сценарий:
    Пусть существует пользователь "user1" с параметрами:
      | admin    | false    |
      | status   | 0        |

  @feature
  Сценарий: Вход в систему в качестве подтвержденного пользователя
    Если авторизоваться пользователем "user1"
    # "Вошли как <логин пользователя>"
    #Отображается элемент "Поиск"
    И на главной странице отображается поле "Домашняя страница"
    И на главной странице отображается поле "Моя страница"
    И на главной странице отображается поле "Проекты"
    И на главной странице отображается поле "Помощь"
    И на главной странице отображается поле "Моя учётная запись"
    И на главной странице отображается поле "Выйти"
    И на главной странице не отображается поле "Администрирование"
    И на главной странице не отображается поле "Войти"
    И на главной странице не отображается поле "Регистрация"
    И на главной странице отображается поле "Строка поиска"
