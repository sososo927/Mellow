package com.example.mellow

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.post_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.post_list)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ポストボタンのクリックリスナーを設定
        val postButton = findViewById<ImageButton>(R.id.post_button)
        postButton.setOnClickListener {
            // Intentを使用してpost_pageに遷移
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }

        // main -> PostListActivity -> Postlist.xml
        val intent = Intent(this, PostListActivity::class.java)
        startActivity(intent)



    }
}