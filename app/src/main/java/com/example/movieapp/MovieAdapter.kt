package com.example.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieCardBinding

// Adapter class for managing movie items in a RecyclerView using an interface for click events.
class MovieAdapter (
    private val movieList: List<MovieModel>,
    private val listener: OnMovieItemClickListener, // Listener passed from the activity
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // ViewHolder class holds the views for each item in the list.
    inner class MovieViewHolder(val binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val moviePic: ImageView = binding.imgMovieCard
        val movieTitle: TextView = binding.txtMovieTitle
        val movieDescription: TextView = binding.txtMovieDescription
        val movieRating: RatingBar = binding.ratingBarMovie
        val moviePrice: Button = binding.btnPrice
        init {
            // Sets an onClickListener for the item view that triggers the listener's function.
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onMovieItemClickListener(movieList[position]) // Triggers the onMovieItemClickListener function
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val item = movieList[position] // Gets the current movie item from the list

        // Bind data to the views
        holder.binding.imgMovieCard.setImageResource(item.moviePic)
        holder.binding.txtMovieTitle.text = item.movieTitle
        holder.binding.txtMovieDescription.text = item.movieDescription
        holder.binding.ratingBarMovie.rating = item.movieRate
        holder.binding.btnPrice.text = String.format("$ %.2f", item.moviePrice)
    }

    // Returns the total number of items in the list.
    override fun getItemCount() = movieList.size
}