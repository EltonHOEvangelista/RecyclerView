package com.example.movieapp

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.ActivityMovieDetailsBinding

// Activity that shows the details of a selected food item.
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using the binding class
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        // Set the root view of the layout
        setContentView(binding.root)

        //get data from main activity using bandle
        val extra: Bundle? = getIntent().getExtras()

        // Retrieve the passed movie item from the intent
        val movieModel: MovieModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movieModel", MovieModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movieModel")
        }

        //Populate views if movieModel is not null
        movieModel?.let {
            binding.imgMovieDetail.setImageResource(it.moviePic) // Sets the image of the movie
            binding.txtMovieTitleDetail.text = it.movieTitle // Sets the name of the movie
            binding.txtMovieDescriptionDetail.text = it.movieFullDescription // Sets the description of the movie
            binding.ratingBarMovieDetail.rating = it.movieRate // Sets the rating of the movie
            binding.btnPriceDetail.text = String.format("$ %.2f", it.moviePrice) // Sets the price of the movie
        }

        if (extra != null) {
            binding.txtAverageRating.text = "Average rating of all movies on the Movie App: " + extra.getFloat("moviesRating").toString()
        }
    }
}
