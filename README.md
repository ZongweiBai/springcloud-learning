# springcloud-learning
A simple example of micro-service created by springboot+springcloud

### Consul

```bash
# start with dev mode
./consul agent -dev
```

### Zipkin

```bash
# download lastest server jar
wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'

# start with 'java -jar'
java -jar zipkin.jar
```

### Hystrix Dashboard

http://localhost:18910/hystrix