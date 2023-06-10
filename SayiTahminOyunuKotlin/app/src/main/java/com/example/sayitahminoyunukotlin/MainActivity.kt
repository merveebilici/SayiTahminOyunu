package com.example.sayitahminoyunukotlin

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var numberInput: EditText
    private lateinit var guessButton: Button
    private lateinit var hintText: TextView
    private lateinit var imageMood: ImageView
    private lateinit var imageView: ImageView

    private var randomNumber = Random.nextInt(1, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberInput = findViewById(R.id.numberInput)
        guessButton = findViewById(R.id.guessButton)
        hintText = findViewById(R.id.hintText)
        imageMood = findViewById(R.id.imageMood)
        imageView = findViewById(R.id.imageView)

        Picasso.get().load("https://clipartcraft.com/images/dice-clipart-black-9.png").into(imageView)
        val rotation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        imageView.startAnimation(rotation)



        guessButton.setOnClickListener {
            val userGuess = numberInput.text.toString().toIntOrNull()
            if (userGuess != null) {
                checkGuess(userGuess)
            } else {
                Snackbar.make(guessButton, "Lütfen geçerli bir sayı girin!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }



    private fun checkGuess(userGuess: Int) {
        when {
            userGuess > randomNumber -> {
                hintText.text = "Tahmininiz çok yüksek! Daha düşük bir sayı deneyin."
                imageMood.setImageResource(R.drawable.baseline_mood_bad_24)
                Snackbar.make(guessButton, "Yüksek tahmin!", Snackbar.LENGTH_SHORT).show()

            }
            userGuess < randomNumber -> {
                hintText.text = "Tahmininiz çok düşük! Daha yüksek bir sayı deneyin."
                imageMood.setImageResource(R.drawable.baseline_mood_bad_24)
                Snackbar.make(guessButton, "Düşük tahmin!", Snackbar.LENGTH_SHORT).show()

            }
            else -> {
                hintText.text = "Tebrikler! Doğru tahmin ettiniz."
                imageMood.setImageResource(R.drawable.baseline_mood_24)
                Snackbar.make(guessButton, "Doğru tahmin!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
