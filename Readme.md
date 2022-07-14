<h1 align="center">SpringDB</h1>

<h2>Список запросов:</h2>
<h4>Базовый URL - https://test-spri.herokuapp.com</h4>
<h3>Регистрация</h3>

* <h4>/signUp/reg - POST запрос на регестрацию пользователя. В теле запроса нужно передать логин, пароль и имя. Сервер совершит проверку логина на совпадение в базе данных, а так же проверку соблюдения требований при написании пароля и имени. Если все в порядке, то пользователь будет успешно зарегестрирован. Если есть какие-то ошибки, то появится сообщение об ошибке.
</h4>
<h4>
Пример: 
https://test-spri.herokuapp.com/signUp/reg<br>
Content: {"login":"USER", "password":"12345", "name":"User"}
</h4>
<h3>Авторизация</h3>

* <h4>/log/enter - POST запрос на авторизацию пользователя. В теле запроса нужно передать логин и пароль. Сервер совершит проверку логина и пароля на совпадения в базе данных. Если все в порядке, то пользователь будет успешно авторизирован. Если есть какие-то ошибки, то появится сообщение об ошибке.
</h4>
<h4>
Пример:
https://test-spri.herokuapp.com/log/enter <br>
Content: {"login":"user", "password":"12345"}
</h4>

<h3>Работа с профилем пользователя</h3>

* <h4>/users/showUser - GET запрос на получение информации о профиле пользователя.  Если пользователь не авторизирован, то появится соответствующее сообщение.
</h4>
<h4>
Пример: https://test-spri.herokuapp.com/users/showUser
</h4>

* <h4>/users/editUser - PATCH запрос на изменение данных пользователя. Если пользователь не авторизирован, то появится соответствующее сообщение.
</h4>
<h4>
Пример: https://test-spri.herokuapp.com/users/editUser
Content:{"name":"user", "old password":"12345", "new password":"54321"}
</h4>

<h3>Работа со статьями</h3>

* /articles - GET запрос на получение таблицы статей.

Пример:  https://test-spri.herokuapp.com/articles

* /articles/show - GET запрос на получение информации об определенной статье.

Пример: https://test-spri.herokuapp.com/articles/show/?id=8

* /articles/new - POST запрос на создание новой статьи.
В теле запроса нужно передать заголовок статьи, ее содержание и имя автора.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: https://test-spri.herokuapp.com/articles/new
Content: {"title":"Spring", "text":"read me", "authour":"user"}

* /articles/edit - PATCH запрос на изменение данных статьи.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: https://test-spri.herokuapp.com/articles/edit
Content: {"title":"Java", "text":"read me Java", "authour":"Admin"}

* /articles/delete - DELETE запрос на удаление статьи.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: https://test-spri.herokuapp.com/articles/delete
Content:
