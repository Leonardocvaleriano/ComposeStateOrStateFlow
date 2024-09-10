# ComposeState vs StateFlow

## Overview

ComposeState(or simply State) and StateFlow are two different mechanisms for managing and observing state in your application.
They have similar purposes but are used in different contexts, particularly in how they interact with the UI and manage state changes.

## ComposeState (or State)

* It is designed to hold and manage UI state within a Compose function
* It is specifically optimized for JetPack Compose's **reactive** UI model, **where UI elements automatically recompose when the state changes**
* **Jetpack Compose automatically triggers a recomposition of the relevant parts of the UI to reflect the updated state**. This makes State a powerful tool for managing dynamic, interactive UI elements.
* It is a Local UI State (Data that is only relevant to a specific composable and doesn't need to be shared across different parts of the app) Management.
```
@Composable
  fun Greeting() {
  var name by remember { mutableStateOf("") }

  TextField(
  value = name,
  onValueChange = { newName -> name = newName },
  label = { Text("Enter your name") }
  )

  Text(text = "Hello, $name!")
  }`
```

## StateFlow
* It's a part of Kotlin's Flow API and is a state-holder observable flow that emits the current state and any subsequent updates to that state. It's a part of Kotlin's couroutines and Flow library.
* Unlike `ComposeState`, `StatFlow` is not specific to Compose and can be used in any Kotlin codebase, making it more versatile across different layers of an app, such as ViewModels and repositories.

## Key Differences

**StateFlow**
  * It's more robust management solution than `ComposableState`
  * Using Flows allows you to use flow operators(map, filter, combine)

**ComposableState**
  * ComposableState is more simple to implement because it contain less code.
  * Its states will be completed tighted to composables projects.
  * You can use it to manage local composable inside a composable function.

## When to Use What?

* Use **ComposeState(or State)** when managing UI state that is local to a composable or a small tree of Composables. It’s simple and directly integrated with Compose’s lifecycle.
* For **ViewModels** is recommended to use **Flows** because of:

  1. Using Flows allows you to use flow operators(map, filter, combine)
  2. Allow us to easy deal of process death with the SavedStateHandle (Keep the last state defined by the user when the app be minimized) more than with ComposableState.
  3. You just keep your ViewModels compose free, that make the ViewModels more reusable, because you can use the same ViewModel for a non-composable project.
  


