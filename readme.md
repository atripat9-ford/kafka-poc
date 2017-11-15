1)Start Zookeeper:
bin/zookeeper-server-start.sh config/zookeeper.properties

2)Start Kafka Server:
bin/kafka-server-start.sh config/server-1.properties

3)Create Topic:
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

4)Verify Topic is created:
bin/kafka-topics.sh --list --zookeeper localhost:2181

5)Start Producer:
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test 

6)Start Consumer:
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning



https://stackoverflow.com/questions/36022358/kafka-avro-consumer-with-decoder-issues
https://stackoverflow.com/questions/8298308/how-to-encode-decode-kafka-messages-using-avro-binary-encoder




CHeck this out
https://docs.spring.io/spring-cloud-stream/docs/Brooklyn.RELEASE/reference/html/contenttypemanagement.html
http://www.beeworks.be/blog/2016/start-streaming-kafka-spring-cloud.html