# playwright-java-demo

## debug latest failure

```shell
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
```

## before running api tests
Clone, build and run the [bookcase-java-app](https://github.com/george-postelnicu/bookcase-java?tab=readme-ov-file#build)
