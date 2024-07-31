# Eric's CodingChallenge

### Main Project Lib Versions

Gradle wrapper `8.6`  
Android Gradle plugin `8.4.1`  
Kotlin Android `1.9.0`  
Core Ktx `1.13.1`  

### Secondary Lib Versions

junit = `4.13.2`  
junitVersion = `1.1.5`  
espressoCore = `3.5.1`  
appcompat = `1.6.1`  
material = `1.12.0`  
constraintlayout = `2.1.4`  
navigationFragmentKtx = `2.7.7`  
navigationUiKtx = `2.7.7`  
coroutines = `1.8.1`  
retrofit = `2.11.0`  

Check the __libs.versions.toml__ file for the complete version library.

## Design choices

The base structure of the project is based on the MVVM architecture pattern.  
The **extension** package contains the Date extension file, with several small utilities for the Date class, can be expanded further as needed.  
The **model** package contains the data classes of the elements queried by the API.  
The **service** package contains the network layer, that is, the repository object accessed by the data layer, the API definition for Retrofit, and the Network Client.
The **view** package contains the fragments, adapters and custom views used on the application.  

Future improvements could be:  
- introduction of live data on the viewModel.
- automated UI tests on the app behavior ( happy paths, error paths ).
- HTTP response error/status handling forwarding UI for message display to users.