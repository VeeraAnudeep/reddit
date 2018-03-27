package com.ab.reddit.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.ab.reddit.models.Topic
import kotlinx.android.synthetic.main.item_topic.view.*

/**
 * Created on 27/03/18.
 */
class TopicViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    fun bindViewHolder(topic: Topic?){
        itemView.content.text = topic?.content
        itemView.upvote_count.text = topic?.nUpVotes.toString()
        itemView.downvote_count.text = topic?.nDownVotes.toString()
    }
}