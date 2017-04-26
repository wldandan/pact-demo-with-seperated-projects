## 运行Consumer端测试

### 生成Consumer和Provider之间的Contract文件
```
$ ./gradlew test
```

### 查看Pact生成的契约文件
```
$ cat target/pacts/*.json
```