# ELK
## Espresso Extension library written in Kotlin
A simple extension library for Espresso with support for the page object design, or in this case, the Robot Pattern. This library is growing and more features to come.  There are a few ways to perform different actions and assertions as well as matching objects.

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

### License
MIT License

Copyright (c) 2020 Josh Ludahl

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.