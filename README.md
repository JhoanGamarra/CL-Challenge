### Features

- Get list of teams that belong to "Spanish la Liga".
- Show the team detail.
- Spinner with league selector(Soccers Leagues)

### Versions

- Kotlin 1.4.32
- Android Studio 4.1.1
- Gradle 4.1.1
- minSdkVersionI: 21
- targetSdkVersion : 29

### Structure

The project is organize in package by fetures and into each feature is organize in package by layer.

- teams
	 - Data
	 - Domain
	 - Presentation

### Architecture 

Clean Architecture + MVVM - ("Screaming Architecture")

![](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.journaldev.com%2Fwp-content%2Fuploads%2F2018%2F04%2Fandroid-mvvm-pattern.png&f=1&nofb=1)

- Layers :
	- Presentation Layer ( Fragments, View Models, LiveData)
	- Domain Layer(UsesCases, Entitites, Repository)
	- Data Layer(Network, Database, Memory)

![](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.imgur.com%2FtJxzrx2.png&f=1&nofb=1)


- Patterns
	- Repository Pattern

![](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmiro.medium.com%2Fmax%2F2260%2F1*xxr1Idc8UoNELOzqXcJnag.png&f=1&nofb=1)



- Dependencies
	- Firebase Crashlytics(https://firebase.google.com/docs/crashlytics/) for realtime crash reporter
	- Glide(https://github.com/bumptech/glide) for image loading
	- Lottie (https://github.com/airbnb/lottie-android) for render animations
	- Retrofit(https://github.com/square/retrofit) http client handler
