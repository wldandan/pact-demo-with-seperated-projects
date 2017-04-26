cd order-consumer && ./gradlew clean test && cd -

export PACT_FILE=$(pwd)/order-consumer/target/pacts/orderConsumer-orderProvider.json
echo "exporing PACT_FILE = $PACT_FILE"

cd order-service
./gradlew clean build pactVerify