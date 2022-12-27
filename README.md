# api-test-rest-assured

Tools Used: Maven, Java, rest-assured, TestNG, json-simple

This is automation API test for endpoint [this url](https://reqres.in/)

# Setting up and running tests

* Open project as Maven Project in Eclipse, Intellij, or other IDE
* Run ReqresTestAPI.java file using TestNG. The directory file:
```
{YourProjectPath}/src/test/java/runner
```
* In that file, there is a scenario API test that consists of several test case:
	* Test Case 1, to get list all of users in the spesific page
	* Test Case 2, to get spesific user using user id from Test Case 1
	* Test Case 3, to create new user
    * Test Case 4, to create new user with username as object