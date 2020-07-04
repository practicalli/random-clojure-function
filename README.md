# random-clojure-function
An application that returns a random function name and description from the Clojure standard library

[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg)](https://circleci.com/gh/practicalli/random-clojure-function)


## Installation
Download from https://github.com/practicalli/random-clojure-function.


## Development
Open the `src/practicalli/random-clojure-function.clj` file in a Clojure editor, evaluate the whole namespace.

Cognitect Labs test runner is included in the `deps.edn` project configuration, for running all tests
```shell
clojure -A:test:runner
```

Run the application on the command line:
```shell
clojure -m practicalli.random-clojure-function
```

The application can take arguments that are names of namespaces available in the [Clojure Standard Library](https://clojure.github.io/clojure/).
```shell
clojure -m practicalli.random-clojure-function "clojure.string" "clojure.repl"
```


## Deployment
Package the application into a JVM jar file, creating an uberjar that contains the application and the Clojure standard library.

```shell
clojure -A:uberjar-depstar
```

Run the application from the uberjar:
```shell
    $ java -jar random-clojure-function.jar
```

## License
Copyright © 2020 Practicalli

Distributed under the Creative Commons Attribution Share-Alike 4.0 International
