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

## Примечание

Для получения доступа к API необходимо указать свой Api Key в файле local.properties

API_KEY="YOUR_API_KEY"

## Скриншоты:


![WeatherAppScreen](https://github.com/irabelova/WeatherApp/assets/135704903/8be02692-7bb0-43fc-b597-ba2b545687fa)
![WeatherAppScreenWithQuery](https://github.com/irabelova/WeatherApp/assets/135704903/9300ea83-419d-40f4-8fef-ae3bf54bbbd3)


## Видео:


https://github.com/irabelova/WeatherApp/assets/135704903/9b7a6f6f-9267-401e-b627-33dd6ceacd5b




