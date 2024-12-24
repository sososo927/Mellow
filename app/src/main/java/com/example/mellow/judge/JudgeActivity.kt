package com.example.mellow.judge

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mellow.MainActivity
import com.example.mellow.R


class JudgeActivity : AppCompatActivity() {

    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_judge)

        // btn_pic_selectを変数上に定義
        var btnPicSelect : Button = findViewById<Button>(R.id.btn_pic_select)
        // ボタン押下時の処理
        btnPicSelect.setOnClickListener{
            if (imageView?.drawable == null) {
                // 画像未選択
                Log.e("TEST", "imageViewはnull")
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply{
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/*"
                }
                startActivityForResult(intent, READ_REQUEST_CODE)
            } else {
                // 画像選択済み
                // TODO: 星座判定処理へ遷移

                // 星座判定処理後結果画面へ遷移
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

    // READ_REQUEST_CODEの定義
    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    // 写真選択後処理
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    resultData?.data?.also { uri : Uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        imageView = findViewById<ImageView>(R.id.img_judge_select)
                        imageView?.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
