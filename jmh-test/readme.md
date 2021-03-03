创建jmh测试项目：mvn archetype:generate ^
            -DinteractiveMode=false ^
            -DarchetypeGroupId=org.openjdk.jmh ^
            -DarchetypeArtifactId=jmh-java-benchmark-archetype ^
            -DgroupId=org.sample ^
            -DartifactId=test ^
            -Dversion=1.0
初始化项目：根目录下 mvn clean verify

运行项目：
    run （Debug模式无法运行）
    java -jar benchmarks.jar JMHTest (需指定测试类，另一个jar包不可用)