# WeatherApp

## Функционал

WeatherApp - приложение, позволяющее узнать прогноз погоды на ближайшие 5 дней для выбранного города.

При отсутствии интернета реализована возможность поиска по кэшу.

## Используемые технологии и архитектура

1) Приложение построено по принципу Clean Architecture с использованием инъекции зависимостей (Dagger).
2) Presentation-слой создан в двух вариантах:
   - в ветке master - с помощью View
   - в ветке compose - с помощью библиотеки Jetpack Compose
   
   В обоих случаях используется MVVM
3) Для кэширования результатов поиска использовался Room
4) Помимо прочего в приложении использованы ViewBinding, Coroutines, Retrofit, Moshi, Coil (отображение иконок)
5) Написаны unit-тесты


## Скриншоты:

![WeatherAppScreen](https://github.com/irabelova/WeatherApp/assets/135704903/635d5230-c251-4659-98b5-d310ccb3f6d7)
![WeatherAppScreenWithQuery](https://github.com/irabelova/WeatherApp/assets/135704903/a0587332-725a-404f-8865-4460de2b5b6b)

## Видео:

https://github.com/irabelova/WeatherApp/assets/135704903/26b88d19-e91d-4bb9-84ef-636519ac8fdc



