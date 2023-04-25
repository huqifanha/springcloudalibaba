https://github.com/alibaba/spring-cloud-alibaba/wiki

https://github.com/alibaba/nacos/releases 

https://github.com/alibaba/Sentinel/releases
https://github.com/alibaba/Sentinel/wiki/控制台
https://github.com/alibaba/Sentinel

http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html
https://github.com/seata/seata/releases
https://github.com/seata/seata/tree/master/script

创建undo_log表： F:\seata\seata-master\script\client\at\db
SELECT ``(t.rollback_info USING utf8) FROM undo_log t

https://skywalking.apache.org/downloads/

启动参数
‐javaagent: skywalking-agent的路径
‐DSW_AGENT_NAME: SW_AGENT自定义服务名
‐DSW_AGENT_COLLECTOR_BACKEND_SERVICES: 连接OAP服务信息(127.0.0.1:11800)

在agent/config/agent.config配置文件，添加如下配置信息，（agent与oap不在部署在同一台服务器需要修改）：
plugin.toolkit.log.grpc.reporter.server_host=${SW_GRPC_LOG_SERVER_HOST:192.168.3.100}
plugin.toolkit.log.grpc.reporter.server_port=${SW_GRPC_LOG_SERVER_PORT:11800}
plugin.toolkit.log.grpc.reporter.max_message_size=${SW_GRPC_LOG_MAX_MESSAGE_SIZE:10485760}
