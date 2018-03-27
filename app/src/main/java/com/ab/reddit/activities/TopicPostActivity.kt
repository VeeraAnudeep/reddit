package com.ab.reddit.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ab.reddit.R
import com.ab.reddit.base.BaseActivity
import com.ab.reddit.viewmodels.RedditViewModel
import kotlinx.android.synthetic.main.activity_topic_post.*
import javax.inject.Inject

class TopicPostActivity : BaseActivity<RedditViewModel>() {
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    override fun getViewModel(): RedditViewModel {
        return model
    }

    @Inject
    lateinit var model: RedditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_post)
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Post Topic"
        submit.setOnClickListener {
            val content = editText.text.toString().trim()
            if (!content.isEmpty()) {
                submit.isEnabled = false
                progress.visibility = View.VISIBLE
                model.post(content)
            }
        }
        model.onPostSubmitted.observe(this,Observer<Boolean>{
            progress.visibility = View.GONE
            submit.isEnabled = true
            if(it==true){
                setResult(Activity.RESULT_OK)
                showToast("Topic Posted!")
                finish()
            }else{
                showToast("Oops! There is some issue.")
            }
        })
    }


    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
