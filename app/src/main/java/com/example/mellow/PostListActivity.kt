package com.example.mellow;
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class PostListActivity : AppCompatActivity() {

    private lateinit var loveButton: ImageButton
    private lateinit var humanButton: ImageButton
    private lateinit var peopleButton: ImageButton
    private lateinit var postButton: ImageButton

    private lateinit var viewPager: ViewPager2

    // ProgressBar の参照
    private lateinit var humanProgressBar: ProgressBar
    private lateinit var loveProgressBar: ProgressBar
    private lateinit var peopleProgressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_list)

        // ProgressBar を初期化
        humanProgressBar = findViewById(R.id.human_progress_bar)
        loveProgressBar = findViewById(R.id.love_progress_bar)
        peopleProgressBar = findViewById(R.id.people_progress_bar)


        // ボタンの初期化
        loveButton = findViewById(R.id.love_button)
        humanButton = findViewById(R.id.human_button)
        peopleButton = findViewById(R.id.people_button)
        postButton = findViewById(R.id.post_button)

        // ViewPager2 の初期化
        viewPager = findViewById(R.id.view_pager)


        // フラグメントリストを作成
        val fragments: List<Fragment> = listOf(
            HumanFragment(),
            LoveFragment(),
            PeopleFragment()
        )

        // ViewPager2 にアダプターを設定
        val adapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = adapter

        // ボタンをクリックしたときの動作
        humanButton.setOnClickListener { viewPager.currentItem = 0 }
        loveButton.setOnClickListener { viewPager.currentItem = 1 }
        peopleButton.setOnClickListener { viewPager.currentItem = 2 }

        // ViewPager2 のページ変更時にボタンの状態を更新
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtonStates(position)
                //Log.d("ViewPager", "現在のページ: $position") // ログにページインデックスを出力

                // ProgressBar の表示を更新
                updateProgressBars(position)

            }
        })

        // 投稿ボタン押下時の動作
        postButton.setOnClickListener {
            // PostPageActivityに遷移
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }

    // ProgressBar の表示を更新
    private fun updateProgressBars(position: Int) {
        humanProgressBar.visibility = if (position == 0) ProgressBar.VISIBLE else ProgressBar.GONE
        loveProgressBar.visibility = if (position == 1) ProgressBar.VISIBLE else ProgressBar.GONE
        peopleProgressBar.visibility = if (position == 2) ProgressBar.VISIBLE else ProgressBar.GONE
    }

    // ボタンの状態を更新するメソッド
    private fun updateButtonStates(position: Int) {
        humanButton.isEnabled = position != 0
        loveButton.isEnabled = position != 1
        peopleButton.isEnabled = position != 2
    }
}