package com.ab.reddit.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ab.reddit.Constants.DOWNVOTE
import com.ab.reddit.Constants.UPVOTE
import com.ab.reddit.R
import com.ab.reddit.viewholders.TopicViewHolder
import com.ab.reddit.listeners.ClickListener
import com.ab.reddit.models.Topic
import kotlinx.android.synthetic.main.item_topic.view.*

/**
 * Created on 27/03/18.
 */
class TopicsAdapter(private val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerView
.ViewHolder>() {

    private var topics: ArrayList<Topic>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_topic,
                parent, false)
        val viewHolder: RecyclerView.ViewHolder
        viewHolder = TopicViewHolder(view)
        view.ic_upvote.setOnClickListener {
            clickListener.onClick(viewHolder.adapterPosition, UPVOTE)
        }
        view.ic_downvote.setOnClickListener {
            clickListener.onClick(viewHolder.adapterPosition, DOWNVOTE)
        }
        return viewHolder
    }

    fun setTopics(topics: ArrayList<Topic>) {
        this.topics = topics
        notifyDataSetChanged()
    }

    fun setVoteCount(topic: Topic,position: Int) {
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return topics?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is TopicViewHolder -> holder.bindViewHolder(topics?.get(position))
        }
    }
}