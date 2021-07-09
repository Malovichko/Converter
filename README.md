# Converter

Отладка и запуск приложения проводились на физическом устройстве Xiaomi mi 9.
Использован MVP подход к проектирования архитектуры.

## Экран выбора валюты
 
![alt text](screenshots/recycler.jpg "")
Дополнительно подключена БД, куда записываются данные котировок. При отсутствии подключения к интернету, данные берутся из БД, если она не пустая.

## Экран загрузки данных

![alt text](screenshots/splashScreen.jpg "")

Splash screen (сделан по гайду https://youtu.be/WZWr0Abomfw)
Да, использование таймеров - не самый хороший подход, но позволяет сделать кастомный дизайн. Первоначальный вариант экрана загрузки предполагал использование XML drawable в res/drawable, где задавались цвет фона и изображение, и кастомная тема, где в качестве фона был XML drawable. Однако сложность заключалась в добавлении тулбара. 

 ## Экран калькулятора
 
 ![alt text](screenshots/converter1.jpg "")
 ![alt text](screenshots/converter.jpg "")
