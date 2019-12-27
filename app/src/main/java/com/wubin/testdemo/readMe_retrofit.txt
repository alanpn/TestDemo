

在Rxjava2中，Observale和Flowable都是用来发射数据流的，但是，我们在实际应用中，很多时候，需要发射的数据并不是数据流的形式，而只是一条单一的数据，
或者一条完成通知，或者一条错误通知。在这种情况下，我们再使用Observable或者Flowable就显得有点大材小用，于是，为了满足这种单一数据或通知的使用场景，
便出现了Observable的简化版——Single、Completable、Maybe。

Single
只发射一条单一的数据，或者一条异常通知，不能发射完成通知，其中数据与通知只能发射一个。

Completable
只发射一条完成通知，或者一条异常通知，不能发射数据，其中完成通知与异常通知只能发射一个

Maybe
可发射一条单一的数据，以及发射一条完成通知，或者一条异常通知，其中完成通知和异常通知只能发射一个，发射数据只能在发射完成通知或者异常通知之前，否则发射数据无效。


HttpLoggingInterceptor setlevel用来设置日志打印的级别，共包括了四个级别：NONE,BASIC,HEADER,BODY
BASEIC:请求/响应行
HEADER:请求/响应行 + 头
BODY:请求/响应航 + 头 + 体