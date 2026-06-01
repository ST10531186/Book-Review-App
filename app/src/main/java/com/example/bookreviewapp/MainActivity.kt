package com.example.bookreviewapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookreviewapp.ui.theme.BookReviewAppTheme

class MainActivity : ComponentActivity() {

    val titles = ArrayList<String>()
    val authors = ArrayList<String>()
    val ratings = ArrayList<Int>()
    val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMainScreen()
    }


    // MAIN SCREEN
    private fun showMainScreen() {
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            showAddScreen()
        }

        findViewById<Button>(R.id.btnView).setOnClickListener {
            showDetailScreen()

        }

        findViewById<Button>(R.id.btnExit).setOnClickListener {
            finish()
        }

    }

    private fun showAddScreen() {
        setContentView(R.layout.screen_add)

        val editTitle = findViewById<EditText>(R.id.editTitle)
        val editAuthor = findViewById<EditText>(R.id.editAuthor)
        val editRating = findViewById<EditText>(R.id.editRating)
        val editComment = findViewById<EditText>(R.id.editComment)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val title = editTitle.text.toString()
            val author = editAuthor.text.toString()
            val ratingText = editRating.text.toString()
            val comment = editComment.text.toString()

            if (title.isEmpty() || author.isEmpty() || ratingText.isEmpty()) {
                Toast.makeText(this, "Fill all field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rating = ratingText.toInt()

            if (rating < 1 || rating > 5) {
                Toast.makeText(this, "Rating must be 1-5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            titles.add(title)
            authors.add(author)
            ratings.add(rating)
            comments.add(comment)

            Toast.makeText(this, " Book Saved", Toast.LENGTH_SHORT).show()

        }

        findViewById<Button>(R.id.btnBackMain).setOnClickListener {
            showMainScreen()
        }

    }

    // DETAIL SCREEN

    private fun showDetailScreen() {
        setContentView(R.layout.screen_detail)

        val txtOutput = findViewById<TextView>(R.id.txtOutput)

        findViewById<Button>(R.id.btnShow).setOnClickListener {

            var output = ""

            for (i in title.indices) {
                output += "Title: ${titles[i]}\n"
                output += "Author: ${authors[i]}\n"
                output += "Ratings: ${ratings[i]}\n"
                output += "Comment: ${comments[i]}\n"

            }
            txtOutput.text = output
        }

        findViewById<Button>(R.id.btnAverage).setOnClickListener {

            if (ratings.isEmpty()) {
                txtOutput.text = "No ratings available"
                return@setOnClickListener

            }

            var sum = 0

            for (r in ratings) {
                sum += r

            }

            val avg = sum.toDouble() / ratings.size

            txtOutput.text = "Average Rating: %2f".format(avg)


        }
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            showMainScreen()
        }
    }
}





