基于契约，运行Order-service的服务端契约测试

## 执行如下命令
```
$ ./run_pact_verify.sh
```

## 或者手动配置PACT FILE的路径并验证
```
$ cd ../order-client
$ ./gradle test
$ cd - 
$ ./gradlew assemble 
$ export PACT_FILE={xxxx} 导出PACT文件的路径
$ ./gradlew pactVerify
```

