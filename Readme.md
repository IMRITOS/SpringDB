<h1 align="center">SpringDB</h1>

<h2>Список запросов:</h2>
<h4>Базовый URL - https://test-spri.herokuapp.com</h4>
<h3>Регистрация</h3>

* <h4>/signUp/reg - POST запрос на регестрацию пользователя. В теле запроса нужно передать логин, пароль и имя. Сервер совершит проверку логина на совпадение в базе данных, а так же проверку соблюдения требований при написании пароля и имени. Если все в порядке, то пользователь будет успешно зарегестрирован. Если есть какие-то ошибки, то появится сообщение об ошибке.
</h4>
<h4>
Пример: <br>
https://test-spri.herokuapp.com/signUp/reg<br>
Content: {"login":"USER", "password":"12345", "name":"User"}
</h4>
<h3>Авторизация</h3>

* <h4>/log/enter - POST запрос на авторизацию пользователя. В теле запроса нужно передать логин и пароль. Сервер совершит проверку логина и пароля на совпадения в базе данных. Если все в порядке, то пользователь будет успешно авторизирован. Если есть какие-то ошибки, то появится сообщение об ошибке.
</h4>
<h4>
Пример:<br>
https://test-spri.herokuapp.com/log/enter <br>
Content: {"login":"user", "password":"12345"}
</h4>

<h3>Работа с профилем пользователя</h3>

* <h4>/users/showUser - GET запрос на получение информации о профиле пользователя. В заголовке запроса нужно передать токен, который был получен при авторизации или регистрации. Если пользователь не авторизирован, то появится соответствующее сообщение.
</h4>
<h4>
Пример: <br>
https://test-spri.herokuapp.com/users/showUser <br>
Headers: Authourization: eyJhbGciOiJIUzUxMiJ9.eyJJZCI6MSwiZXhwIjoxNjU3ODIxMjYzLCJQYXNzd29yZCI6IjEyMzQ1In0.dlpOluGZYhcQhwKOxXj0ervRsNjM6NyZJQPS4DyJ0sIqh13nBfrprdUy8KmE3sCvpBeqeWsoA9SrpQDupNAqVg
</h4>

* <h4>/users/editUser - PATCH запрос на изменение данных пользователя. В заголовке запроса нужно передать токен, который был получен при авторизации или регистрации, так как данный запрос доступен только авторизированному пользователю. В теле запроса нужно передать новое имя, старый пароль для потверждения и новый пароль для изменения. Если пользователь не авторизирован, то появится соответствующее сообщение.
</h4>
<h4>
Пример: <br>
https://test-spri.herokuapp.com/users/editUser <br>
Content:{"name":"user", "old password":"12345", "new password":"54321"} <br>
Headers: Authourization: eyJhbGciOiJIUzUxMiJ9.eyJJZCI6MSwiZXhwIjoxNjU3ODIxMjYzLCJQYXNzd29yZCI6IjEyMzQ1In0.dlpOluGZYhcQhwKOxXj0ervRsNjM6NyZJQPS4DyJ0sIqh13nBfrprdUy8KmE3sCvpBeqeWsoA9SrpQDupNAqVg
</h4>

<h3>Работа со статьями</h3>

* /articles - GET запрос на получение таблицы статей.

Пример:  https://test-spri.herokuapp.com/articles

* /articles/show - GET запрос на получение информации об определенной статье.

Пример: https://test-spri.herokuapp.com/articles/show/?id=8

* /articles/new - POST запрос на создание новой статьи.
В теле запроса нужно передать заголовок статьи, ее содержание и имя автора.
В заголовке запроса нужно передать токен, который был получен при авторизации или регистрации, так как данный запрос доступен только авторизированному пользователю.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: <br>
https://test-spri.herokuapp.com/articles/new <br>
Content: {"title":"Spring", "text":"read me", "authour":"user"} <br>
Headers: Authourization: eyJhbGciOiJIUzUxMiJ9.eyJJZCI6MSwiZXhwIjoxNjU3ODIxMjYzLCJQYXNzd29yZCI6IjEyMzQ1In0.dlpOluGZYhcQhwKOxXj0ervRsNjM6NyZJQPS4DyJ0sIqh13nBfrprdUy8KmE3sCvpBeqeWsoA9SrpQDupNAqVg

* /articles/edit - PATCH запрос на изменение данных статьи.
В теле запроса нужно передать id изменяемой статьи, новый заголовок статьи, новое содержание и имя автора.
В заголовке запроса нужно передать токен, который был получен при авторизации или регистрации, так как данный запрос доступен только авторизированному пользователю.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: <br>
https://test-spri.herokuapp.com/articles/edit <br>
Content: {"id":"1", "title":"Java", "text":"read me Java", "authour":"Admin"} <br>
Headers: Authourization: eyJhbGciOiJIUzUxMiJ9.eyJJZCI6MSwiZXhwIjoxNjU3ODIxMjYzLCJQYXNzd29yZCI6IjEyMzQ1In0.dlpOluGZYhcQhwKOxXj0ervRsNjM6NyZJQPS4DyJ0sIqh13nBfrprdUy8KmE3sCvpBeqeWsoA9SrpQDupNAqVg

* /articles/delete - DELETE запрос на удаление статьи.
В заголовке запроса нужно передать токен, который был получен при авторизации или регистрации, так как данный запрос доступен только авторизированному пользователю.
Если пользователь не авторизирован, то появится соответствующее сообщение.

Пример: <br>
https://test-spri.herokuapp.com/articles/delete/?id= (id статьи, которую вы хотите удалить) <br>
Headers: Authourization: eyJhbGciOiJIUzUxMiJ9.eyJJZCI6MSwiZXhwIjoxNjU3ODIxMjYzLCJQYXNzd29yZCI6IjEyMzQ1In0.dlpOluGZYhcQhwKOxXj0ervRsNjM6NyZJQPS4DyJ0sIqh13nBfrprdUy8KmE3sCvpBeqeWsoA9SrpQDupNAqVg
