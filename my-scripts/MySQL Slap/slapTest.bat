#                                            并发数             测试轮数          测试数据库                      测试脚本          测试存储引擎     查询数
mysqlslap -h106.15.251.107 -uroot -pchengw --concurrency=100 --iterations=100 --create-schema=columbia_limadw --query=test.sql --engine=innodb --number-of-queries=5000 --debug-info
pause