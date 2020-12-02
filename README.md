# ELK
## Espresso Extension library written in Kotlin
## More to come, initial units

### Multiple ways to check views
A simple, bulk check:
```
bulkIsDisplayed(
    view("Text to check),
    view(R.id.id_resource_to_check),
    view(R.string.string_resource_to_check)
)
```