# 什么是物联网平台
> 文档来自阿里云物联网平台-产品简介-[什么是物联网平台](https://help.aliyun.com/document_detail/30522.html?spm=a2c4g.11186623.6.545.b54b2865qYuG43)

更新时间：2020-12-22 11:36:39

 
阿里云物联网平台为设备提供安全可靠的连接通信能力，向下连接海量设备，支撑设备数据采集上云；向上提供云端API，服务端通过调用云端API将指令下发至设备端，实现远程控制。

物联网平台消息通信流程图如下。


实现设备消息的完整通信流程，需要您完成设备端的设备开发、云端服务器的开发（云端SDK的配置）、数据库的创建、手机App的开发。

下文介绍物联网平台消息通信链路。


## 上行数据链路

- 设备通过MQTT协议与物联网平台建立长连接，上报数据（通过Publish发布Topic和Payload）到物联网平台。
- 您可配置规则引擎，编写SQL对上报数据进行处理，并配置转发规则，将处理后的数据转发到RDS、表格存储、函数计算、TSDB、企业版实例内的时序数据存储、DataHub、消息队列RocketMQ等云产品中，或通过AMQP消费组流转到您的ECS服务器上。

## 下行指令链路

- ECS业务服务器调用基于HTTPS协议的API接口Pub，给Topic发送指令，将数据发送到物联网平台。
- 物联网平台通过MQTT协议，使用Publish发送数据（指定Topic和Payload）到设备端。

<!-- ex_nolevel -->