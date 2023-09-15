<div align='center'>

<h1>Cloning IMDB App </h1>
<p>Cloned the IMDB app. </p>

</div>

# :notebook_with_decorative_cover: Table of Contents

- [About the Project](#star2-about-the-project)
<p> <B>1. Project Structure and Architecture:</B>

I structured the project using the MVVM (Model-View-ViewModel) architecture with Clean Architecture principles to separate concerns. This will help maintain a clean and scalable codebase.

**2. Networking and API Calls:**

Used Retrofit for making API calls to TMDB. Implemented a repository pattern to abstract data sources (remote and local).

**3. Caching Images:**

To cache images with a 1-day expiration, i used the library Glide with an appropriate caching strategy. Ensuring that the cached images are cleared after their expiration.

**4. Navigation:**

For the main navigation using tabs, i used the Android Navigation Component. Defined a navigation graph that represents the different screens and their connections.

**5. UI Components:**

- Used a RecyclerView with appropriate adapters for displaying lists of movies.
- For the horizontal scrollable movies preview, i used a RecyclerView with a horizontal LinearLayoutManager.
- Used fragments for the different sections of the home page, search, and favorites.
- For the movie details page, i created a separate fragment.

**7. Local Storage:**

For saving favorite movies locally, I used Room Database. Created an entity and DAO for managing favorite movies. Implemented a repository that handles both remote and local data sources.

**8. Version Control:**

Used Git for version control and used GitHub for collaborative development. </p>

## :star2: About the Project
### :space_invader: Tech Stack
<details> <summary>Client</summary> <ul>
<li><a href="">Android</a></li>
<li><a href="">MVVM</a></li>
</ul> </details>
<details> <summary>Database</summary> <ul>
<li><a href="">Room</a></li>
<li><a href="">SQLite</a></li>
</ul> </details>
