## 基于Pact构建的契约测试

#### 简介

* order-consumer作为消费者，使用order-service提供的数据
* order-service作为提供者，提供数据给order-consumer

#### 运行如下命令，演示消费者契约的生成和验证提供者的流程
```
./run_pact.sh
```


#### 或者手动执行

##### 消费者生成契约
```
$  cd order-consumer
$  ./gradlew test
```

#### 使用契约验证提供者

```
$ cd order-service
$ ./run_pact_verify.sh
```


