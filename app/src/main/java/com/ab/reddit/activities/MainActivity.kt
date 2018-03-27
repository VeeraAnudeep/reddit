package com.ab.reddit.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ab.reddit.Constants.REQUEST_CODE
import com.ab.reddit.R
import com.ab.reddit.adapters.TopicsAdapter
import com.ab.reddit.base.BaseActivity
import com.ab.reddit.listeners.ClickListener
import com.ab.reddit.models.Topic
import com.ab.reddit.viewmodels.RedditViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<RedditViewModel>(), ClickListener, SwipeRefreshLayout.OnRefreshListener {

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        model.getTopics()
    }

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

    private var adapter: TopicsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        list.layoutManager = LinearLayoutManager(this)
        adapter = TopicsAdapter(this)
        swipeRefreshLayout.setOnRefreshListener(this)
        list.adapter = adapter
        fab.setOnClickListener { view ->
            startActivityForResult(Intent(this, TopicPostActivity::class.java),
                    REQUEST_CODE)
        }
        model.getTopics()
        progress.visibility = View.VISIBLE
        onTopicsFetched()
        onShowZeroCase()
    }

    /*
    * Show zero case when there are no Topics to display
    * */
    private fun onShowZeroCase() {
        model.showZeroCase.observe(this, Observer<Boolean> {
            if (it != false) {
                zeroCase.visibility = View.VISIBLE
            } else {
                zeroCase.visibility = View.GONE
            }
        })
    }

    /**
     * @param position position of item clicked in list
     * @param type is either Upvote or Downvote
     * */
    override fun onClick(position: Int, type: String) {
        model.vote(position, type)
    }

    /*
    * Set topics to adapter do display
    * */
    private fun onTopicsFetched() {
        model.topics.observe(this, Observer<ArrayList<Topic>> {
            it?.let {
                progress.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                adapter?.setTopics(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            model.getTopics()
        }
    }
}
