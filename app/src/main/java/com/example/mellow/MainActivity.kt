package com.example.mellow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mellow.judge.JudgeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /**
         * 追加 2024/12/18 判定画面遷移テスト用ボタン
         */
        val btn: Button = findViewById<Button>(R.id.btn_jump_page)
        btn.setOnClickListener {
            Toast.makeText(this, "タップ", Toast.LENGTH_LONG).show()
            val intent = Intent(this, JudgeActivity::class.java)
            startActivity(intent)
        }

    }
}