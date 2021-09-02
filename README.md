Для задания Домашняя библиотека из DB & Repositories реализовать паттерн MVC и создать веб приложение с использованием tomcat. 
В интерфейсе должны быть реализованы все user stories из задания Internal Java Lab Design and Architecture. 
Для верстки страниц необходимо использовать toolkit bootstrap. 
https://getbootstrap.com/
При работе с авторизацией используй JWT. 
1 варинт: Запуск tomcat: 
mvn clean install war:war
Из target перемещаю war в webapp (tomcat), далее localhost -> appmanager (login: tomcat, pass: qwerty)-> перезапустить -> ссылка HomeLibraryWeb
Запуск tomcat -> bin -> startup.bat
2 вариант: mvn clean install tomcat7:run

