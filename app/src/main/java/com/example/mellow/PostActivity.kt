package com.example.mellow

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class PostActivity : AppCompatActivity() {

    private var isImageSelected = false // 画像が選択されているかのフラグ(追加)

    private val viewPager: ViewPager2? = null
    private val loveButton: ImageButton? = null
    private var humanButton: ImageButton? = null
    private var peopleButton: ImageButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_page)

        // UIコンポーネントの取得
        val cancelButton = findViewById<Button>(R.id.cancel_button) // キャンセルボタン
        val submitButton = findViewById<Button>(R.id.submit_button) // ポストボタン
        val iconView = findViewById<ImageView>(R.id.user_icon) // アイコン画像
        val editableTextView = findViewById<TextView>(R.id.editable_text_view) // テキスト内容
        val imageView = findViewById<ImageView>(R.id.photo_View) // 背景画像
        val photoButton = findViewById<ImageButton>(R.id.photo_Button) // 写真ボタン

        // テキストの取得例
        val userInputText = editableTextView.text.toString()

        // キャンセルボタンのクリックイベント
        cancelButton.setOnClickListener {
            // キャンセル処理: ここでアクティビティを終了する、または別の画面に遷移
            finish() // アクティビティ終了
        }

        // ポストボタンのクリックイベント
        submitButton.setOnClickListener { // 投稿内容の取得やバリデーション処理
            //                String postContent = getPostContent(); // 投稿内容を取得する独自のメソッド
            //                if (postContent.isEmpty()) {
            //                    Toast.makeText(PostActivity.this, "投稿内容が空です", Toast.LENGTH_SHORT).show();
            //                    return;
            //                }

            val userInputText = editableTextView.text.toString().trim() // 入力内容を取得
            if (userInputText.isEmpty() && !isImageSelected) {
                // 入力が空の場合、警告メッセージを表示
                Toast.makeText(this, "テキストまたは画像を入力してください", Toast.LENGTH_SHORT).show()
            } else {
                // トーストで投稿完了メッセージを表示
                Toast.makeText(this@PostActivity, "投稿完了", Toast.LENGTH_SHORT).show()

                // 投稿処理（例: データベース保存やAPI送信）
                // sendPostData(postContent);

                // PostListActivityに遷移
                val intent = Intent(
                    this@PostActivity,
                    PostListActivity::class.java
                )
                startActivity(intent)

                // 現在のアクティビティを終了する場合
                finish()
            }
        }


        // 写真ボタンのクリックイベント
        photoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, READ_REQUEST_CODE)
        }
    }

   companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode != RESULT_OK) {
            return
        }
        when(requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    resultData?.data?.also { uri: Uri ->
                        val inputStream = contentResolver?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        val imageView = findViewById<ImageView>(R.id.photo_View)
                        imageView.setImageBitmap(image)
                        isImageSelected = true // 画像が選択されたことを記録
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
