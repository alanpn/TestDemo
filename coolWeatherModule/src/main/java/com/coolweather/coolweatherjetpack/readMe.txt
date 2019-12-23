
MainViewModel

MainModelFactory

PlaceRepository
    CoolWeatherDatabase
        PlaceDao / WeatherDao : 数据库
    CoolWeatherNetwork 网络调用 返回结果
        ServiceCreator : Retrofit 初始化
        PlaceService / WeatherService : Retrofit调用 