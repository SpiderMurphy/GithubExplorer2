package cyan.com.githubexplorer.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cyan.com.githubexplorer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(appToolbar)
    }
}
