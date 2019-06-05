使用zookeeper实现分布式数据同步:
两种同步方式:
1.通过监听zookeeper,在内存中维护一个map,调用map.get("xxx")即可获取:
Springboot的配置文件spring.data.sync.type配置成map,
spring.data.sync.receive设置成true,
并注入MapReceiveDataSyn类,则可以调用getMap方法获取维护的map信息,或者调用getByKey方法获取具体值,
在项目中如果想往map中添加数据则会抛出RuntimeException
2.监听zookeeper并调用指定方法
Springboot的配置文件spring.data.sync.type配置成send,
spring.data.sync.sendClass配置成接收推送的类
spring.data.sync.deleteMethod:接收delete数据的方法
spring.data.sync.sendMethod:接收insert或update数据的方法
则使用这个方式维护数据
3.需修改数据的时候调用zookeeper的推送同步数据
Springboot的配置文件spring.data.sync.send设置为true则使用推送,
注入SendDataSyn,并调用send修改或插入数据,delete删除数据
4.其他配置:
spring.data.sync.sessionTimeOut //zookeeper的session超时时间
spring.data.sync.connectTimeOut //zookeeper链接超时时间
spring.data.sync.connectString //zookeeper的链接串如172.0.0.1:2181
spring.data.sync.maxRetries //链接zookeeper重试次数
