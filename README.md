[![Java CI](https://github.com/opifexM/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/opifexM/java-project-78/actions/workflows/main.yml)
[![hexlet-check](https://github.com/opifexM/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/opifexM/java-project-78/actions/workflows/hexlet-check.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/9535239562c7bd7ee9b5/maintainability)](https://codeclimate.com/github/opifexM/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/9535239562c7bd7ee9b5/test_coverage)](https://codeclimate.com/github/opifexM/java-project-78/test_coverage)

## Data validator
A library for checking the correctness of any data.

**Validation of strings**
Control methods:
* _required_ – any non-empty string.
* _minLength_ – the string is equal to or longer than the specified number.
* _contains_ – the string contains a specific substring.

**Validation of numbers**
Control methods:
* _required_ – any number, including zero.
* _positive_ – positive number.
* _range_ – the range in which the numbers, including the boundaries.

**Validation of Map type objects**
Control methods:
* _required_ – Map data type
* _sizeof_ - is required, the number of key-value pairs in the Map object must be equal to the specified.
* _shape_ - allows describing validation for Map object values by keys.
##
