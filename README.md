#Экзамен. Задание 1

###Описание:
Разработать проект автотестов с использованием junit, maven, selenide, allure За основу тестов взять Лекцию с ui тестами Jira.    
Тестируемый сайт: http://edujira.ifellow.ru.

###Сборка:
Maven, Selenide, Junit5, Allure.

###Входные данные:
Входные константы берутся из resources/test.properties. Реализовано через System properties.

###Запуск:
`mvn clean test`

###Построение отчета:
`mvn allure:serve`

