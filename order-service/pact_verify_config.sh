cp ../order-client/target/pacts/*.json build/
export PACT_FILE=$(pwd)/build/orderConsumer-orderProvider.json
