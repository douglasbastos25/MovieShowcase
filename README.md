<div align="center">

English | [Portuguese](docs/README.pt.md)

</div>

# The App

It's an app I developed in Kotlin. It
accesses [The Movie DB API](https://developers.themoviedb.org/3/getting-started/introduction) and
displays information about a movie, as well as a list of similar ones.

## Stack

- **Koin** for dependency injection
- **Retrofit2** for API access
- **Glide** for image handling
- **Coroutines** for multithreading
- **Lifecycle** to work with LiveData and ViewModels
- **Gson** for JSON handling
- **JUnit4** for testing
- **MockK** and **MockWebServer** to mock dependencies
- **Espresso** for UI testing

## More on the coding and functionalities

<br />

<div align="center">

![Static image](docs/imgs/print_app.png)

<br />

https://user-images.githubusercontent.com/62512714/133094694-1b0ed908-36d9-4774-a361-3b120a7ab813.mp4

</div>

<br />

I followed the requirements presented in the briefing and tried to use every aspect of it as a
learning opportunity. That being said, some elements I worked with and solutions I offered that I
think could kindle some previous considerations are:

#### SharedPreferences:

I chose to use it to add data persistence for the like button, but the mere demonstration of the
selection behavior, as required, could be achieved by:

```kotlin
liked = !liked
binding.btLike.isSelected = liked
```

This code snippet would be inside the click event of the button with the "liked" as a Boolean variable.

#### Clean architecture:

Since the briefing offered it as a possibility, I decided to practice using this specific
architecture because some classes I've taken have introduced it but haven't explained it in detail.
That's also the reason why I decided to work with Koin for dependency injection.

As the saying goes, practice makes perfect, and even though I'm far from it, I can understand it a
little bit more now than I could back then.

#### Protecting sensitive data:

Looking on the internet about how to secure my API Key (since it's necessary for the App to work,
but also a personal key), I came across this principle.

It's not a good practice to give sensitive data away on open repositories. Because of that, I
decided to work with this solution (storing it in a system file not sent to the public repository).
That's why the last version of it probably won't be working for you. To make it so, you have to, on
your *local.properties* file, create:

```groovy
tmdb_api_key = "<Your API Key>"
```

And use a valid API Key.

#### Testing:

That is an area I've been focusing on recently because it's a fundamental aspect of a developer's
routine but neglected in courses and classes.

For that reason, I've practiced here with some unit, instrumented and GUI tests but haven't offered total
coverage for the code. That's also why, on the UI tests, I presented *Lock* and *Thread.Sleep* as a
solution to bypass the multithreading aspect of the App even when the documentation discourages this
behavior.

Instead, it suggests working with Idling Resources, but I admit I need to study and practice more
with this tool.

#### Handling errors:

In the code, there's space for handling errors, as I did in previous projects. For example, No
connection, Invalid API Key, Missing API Key, Alert about changes in the response from the
endpoints, etc. Unfortunately, I ended up using more time with the tests but intend to implement them in
the future (as well as some other improvements I already have in mind).

I think these are enough highlights about the code for now but I'll be happy to answer any questions
about the solutions I've implemented in this project, talk about aspects I could improve on, as well
as discuss opportunities to practice and learn even more.

It's been a great opportunity to develop this project and a lot of fun. For that, I thank M2Y and
Bruna Valério.


<br />

<div align="center">

![M2Y](docs/imgs/logo_m2y.png)

</div>
