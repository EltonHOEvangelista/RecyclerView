/*
CSIS 4280-050
Student: Elton Evangelista (id 300371029)
Instructor: Professor Stephen Chiong
Activity 04
 */

package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMovieItemClickListener {

    //binding variable declaration
    private lateinit var binding: ActivityMainBinding

    //List of movies
    private lateinit var movieList: List<MovieModel>

    private var averageMovieRating: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using the binding class
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the root view of the layout
        setContentView(binding.root)

        //loading list of movies
        movieList = listOf(
            MovieModel(R.drawable.forest_gump,
                "Forest Gump",
                "A simple man’s extraordinary life through decades of American history.",
                "The heartwarming story of Forrest Gump, a man of limited intelligence but boundless love and optimism, who unwittingly influences key historical moments in America. From football stardom to the Vietnam War, Forrest's journey is both touching and inspiring.",
                4.5f,
                9.90f),
            MovieModel(R.drawable.goonies,
                "The Goonies",
                "Kids embark on a treasure hunt to save their homes.",
                "A group of kids from a small town, known as the Goonies, discover a treasure map and set out on an adventurous quest to find a pirate's fortune. Their mission is to save their homes from foreclosure while outwitting dangerous criminals.",
                3.5f,
                6.90f),
            MovieModel(R.drawable.star_wars_new_hope,
                "Star Wars - A New Hope",
                "A farm boy joins rebels to defeat a galactic empire.",
                "Luke Skywalker, a young farm boy, discovers his destiny as a Jedi Knight. He joins forces with Princess Leia, Han Solo, and the Rebel Alliance to fight against the evil Galactic Empire, culminating in the destruction of the Empire’s powerful Death Star.",
                4.0f,
                7.90f),
            MovieModel(R.drawable.titanic,
                "Titanic",
                "A love story aboard the ill-fated voyage of the Titanic.",
                "A sweeping romance between Jack, a poor artist, and Rose, a wealthy socialite, unfolds aboard the Titanic, the \"unsinkable\" ship that meets a tragic fate. The film captures both the human tragedy and the love that blossoms amidst disaster.",
                3.5f,
                8.90f),
            MovieModel(R.drawable.top_gun,
                "Top Gun",
                "Maverick competes in an elite fighter pilot school.",
                "Maverick, a hotshot fighter pilot, enrolls in the elite Top Gun Naval Fighter Weapons School. As he faces intense competition, he learns valuable lessons about leadership, teamwork, and love, while pushing the limits of his flying skills.",
                4.5f,
                9.90f)
        )

        //call function to calculate average movie rating
        calculateAverageMovieRating()

        // Creates an adapter and sets it up with the RecyclerView, passing `this` as the listener
        val adapter = MovieAdapter(movieList, this)
        binding.movieRecyclerView.adapter = adapter // Sets the adapter to the RecyclerView
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this) // Sets the layout manager to arrange items linearly
    }

    override fun onMovieItemClickListener(movieModel: MovieModel) {
        // Intent to navigate from MainActivity to MovieDetailActivity
        val intent = Intent(this, MovieDetailsActivity::class.java)
        // Passes the clicked food item to the detail activity
        intent.putExtra("movieModel", movieModel)
        intent.putExtra("moviesRating", averageMovieRating)
        startActivity(intent) // Starts the detail activity
    }
    //function to calculate average movie rating
    fun calculateAverageMovieRating() {
        var sumRating: Float = 0.0f
        for (movie in movieList) {
            sumRating += movie.movieRate
        }
        averageMovieRating = sumRating / movieList.size
    }
}