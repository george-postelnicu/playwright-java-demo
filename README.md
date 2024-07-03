# playwright-java-demo

## before running any test
```shell
cp .env.example .env
```

```shell
# to run ui tests where we have state (cookies + localStorage) injected into each browser session
./mvnw exec:java -e -D exec.mainClass=org.example.UiState
```
clone, build and run the [bookcase-java-app](https://github.com/george-postelnicu/bookcase-java?tab=readme-ov-file#build)

## running all tests
```shell
./mvnw clean test
# or
./mvnw clean test -Dtags="regression"
```


## running api tests

```shell
# to run all the api tests
./mvnw clean test -Dtags="api-test"
```

```shell
# to run a subgroup of the api tests choose any of the following
./mvnw clean test -Dtags="java-api"
./mvnw clean test -Dtags="rest-assured-api"
./mvnw clean test -Dtags="playwright-api"
```

## running ui tests

```shell
./mvnw clean test -Dtags="ui-test"
```

## debug latest ui failure

```shell
./mvnw exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
```
