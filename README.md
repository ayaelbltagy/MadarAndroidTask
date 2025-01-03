1.Madar Android task required for skills approvel.


2.Task description Suppose you have a user and you want to take information like name, age, job and gender
is has two page first page for take this info and save it local database. and sceond page for showing this info as a list.


3. Architecture
I depend on Modulations and DI to inject this data for view model
Repository is responable for get source of data which in our case is room database.
About Design pattern : MVVM -> to separates views from business logic.
Kotlin coroutines -> to simplify code that executes asynchronously.
 Dagger hilt -> for DI Jetpack compose.
 Material Design 3 -> for ui design.

