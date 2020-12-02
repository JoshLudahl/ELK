# ELK
## Espresso Extension library written in Kotlin

### Built in Robot Pattern support for page object design
Create an object, then call your object:
```kotlin
screen<PageObjectToUse> {
    performSomething()
}

verify<PageObjectToVerify> {
    checkSomething()
}
```

### Simple infix notation to simplify code expression
Clicking on an object is easy:
```kotlin
click on view(R.id.id_resource)
type into view(R.id.textview_item)

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