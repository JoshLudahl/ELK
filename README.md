[![](https://jitpack.io/v/JoshLudahl/ELK.svg)](https://jitpack.io/#JoshLudahl/ELK)
# ELK
## Espresso Extension library written in Kotlin
A simple extension library for Espresso with support for the page object design, or in this case, the Robot Pattern. This library is growing and more features to come.  There are a few ways to perform different actions and assertions as well as matching objects.

### Get started
To include the library, add the following to your app module build.gradle file:
```groovy
dependencies {
    androidTestImplementation "com.github.JoshLudahl:ELK:$elk_version"
}
```
And be sure to include jitpack in the repositories:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

### Simple infix notation to simplify code expression
Clicking on an object is easy:
```kotlin
click on view(R.id.id_resource)
```

Type text into a TextView:
```kotlin
type text("text") into(R.id.textview_item)
```

Basic assertions are human readable:
```kotlin
view(R.id.id_resource) confirm isDisplayed
```

### Multiple ways to check views
A simple, bulk check:
```kotlin
bulkIsDisplayed(
    view("Text to check"),
    view(R.id.id_resource_to_check),
    view(R.string.string_resource_to_check)
)
```

### Extensions utilize both ViewInteractions, Matchers, and some sugar syntax utilizing Kotlins infix notation
For example ViewMatchers.isDisplayed() can be used as part of a ViewInteraction, Matcher, or as part of an assertion:
```kotlin
view(id) check isDisplayed
onView(withId(id)).isDisplayed()
withId(id).isDisplayed()
R.id.id_resource.isDisplayed()
```
This allows for flexibility, so you can customize how your testing library looks and feels. 

### Annotations out of the box for Gherkin support and code separation
Gherkin useage:
```kotlin
fun simpleCheck() {
    @Given("I am on the home page")
    @And("I am not logged in")
    @When("I click on the login link")
    @Then("I am taken to the login page")
}
```

Separate Production, Development, and Local tests:
```kotlin
@Development
@Test
fun checkSomething() {
...
}

@Production
@Test
fun checkSomething() {
...
}

@Local
@Test
fun checkSomething() {
...
}
```
Then just use a runner or command argument to exclude the desired tests:
```groovy
testInstrumentationRunnerArgument "notAnnotation", "com.android.elk.common.Production"
```

### RecyclerView
Various RecyclerView extensions, still working to make them simpler to use.

### Checking Toasts is easy
Simply call the toastMatcher function with the expected string:
```kotlin
toastMatcher("My Message")
```

### Changing orientation is easy
Call the Espresso orientation changer:
```kotlin
rotateOrientationToLandscape()
rotateOrientationToPortrait()
toggleOrientation()
``` 

### Includes an Espresso setup rule
Included in the setup is the Intents, Idling Resources, and DataBindingIdlingResource registering and unregistering.
Includes the suggested IdlingResource class from Android, which you can copy to your production code if you want to use it.

To use, set the rule up and send your activity rule as a parameter:
```kotlin
@get:Rule
val espressoSetupRule = EspressoSetupRule(activityRule)
```
If used, it will register/unregister:
* IdlingResources
* DataBindingIdlingResources
* Intents

### Test Configuration and Utilities
Simple utility functions for accessing configurations such as string resources:
```kotlin
context stringOf id.string.some_string
```

Assets:
```kotlin
assets
```

Instrumentation:
```kotlin
instrumentation
```
Simple, but useful way to easily get access to the applications configuration.

### Now with Dokka
You can now generate Dokka documents with this library.

### Disclaimer
This project is under development and is not suggested for production development at this time.

### License
MIT License

Copyright (c) 2020 Josh Ludahl

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.