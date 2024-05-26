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
picasso = `2.8`  
picassoTransformations = `2.4.0`  
retrofit = `2.11.0`  

Check the __libs.versions.toml__ file for the complete version library.

## Setup

You need to define a `String` named "newsApiKey" with your `newsapi.org` API key
somewhere in your local (project - do not commit) or local (system/user) gradle.properties 
files for the project to compile successfully.  

## Design choices

The base structure of the project is based on the MVVM architecture pattern.  
The **extension** package contains the Date extension file, with several small utilities for the Date class, can be expanded further as needed.  
The **model** package contains the data classes of the elements queried by the API.  
The **service** package contains the network layer, that is, the repository object accessed by the data layer, the API definition for Retrofit, and the Network Client.  
The **utils** package contains a few minor utilities extracted from the code for re-usability purposes.  
The **view** package contains the fragments, adapters and custom views used on the application.  

Future improvements could be:  
- introduction of live data on the viewModel.  
- automated UI tests on the app behavior ( happy paths, error paths ).  
- unit tests on voice command parsing, error parsing.  
- response error/status handling to UI for message display to users.  

### Documentation/guides used

> Continuous speech by Rumit Patel -> https://medium.com/@rumit.patel5/continuous-speech-recognition-on-android-f7c640a3e57b  
> NewsApi Documentation -> https://newsapi.org/docs/  