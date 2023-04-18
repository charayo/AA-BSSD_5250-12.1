package com.example.javakotlin

import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MoodActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    var pathfile: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)
        val imageView = findViewById<View>(R.id.imageView) as ImageView
        val intent = intent
        val choice = intent.getStringExtra(EXTRA_CHOICE)
        if (choice == "Blue") {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.BLUE)
            pathfile = "rain"
            imageView.setImageResource(R.drawable.img)
        } else if (choice == "Yellow") {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.YELLOW)
            pathfile = "horn"
            imageView.setImageResource(R.drawable.yellow_img)
        } else {
            findViewById<View>(R.id.mood_layout).setBackgroundColor(Color.GREEN)
            pathfile = "bell"
            imageView.setImageResource(R.drawable.green_img)
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        val path = "android.resource://" + this.packageName + "/raw/" + pathfile
        val uri = Uri.parse(path)
        try {
            mediaPlayer!!.setDataSource(applicationContext, uri)
            mediaPlayer!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
    }

    companion object {
        const val EXTRA_CHOICE = "com.example.javakotlin.EXTRA_CHOICE"
    }
}