cd ../order-consumer && ./gradlew test && cd -

cp ../order-consumer/target/pacts/orderConsumer-orderProvider.json build/
export PACT_FILE=$(pwd)/build/orderConsumer-orderProvider.json
echo "exporing PACT_FILE = $PACT_FILE"

./gradlew build pactVerify