package com.santino.Article.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.santino.Article.R
import com.santino.Article.model.ArticlePojo
import de.hdodenhof.circleimageview.CircleImageView

class ArticlesAdapter(var context: Context, var articlesList: ArrayList<ArticlePojo>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    var likesCount: String? = null
    var commentsCount: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.adapter_article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if(articlesList.size > position) {
            holder.tv_UserName.text = articlesList[position].user!![0].name
            holder.tv_Description.text = articlesList[position].user!![0].designation
            holder.tv_articleContent.text = articlesList[position].content
            holder.tv_articleTitle.text = articlesList[position].media!![0].title
            holder.tv_articleUrl.text = articlesList[position].media!![0].url
            likesCount = if (articlesList[position].likes!!.toInt() < 1000)
                articlesList[position].likes
            else {
                articlesList[position].likes!!.toInt().rem(1000).toString() + "K"
            }
            commentsCount = if (articlesList[position].comments!!.toInt() < 1000)
                articlesList[position].comments
            else {
                articlesList[position].comments!!.toInt().rem(1000).toString() + "K"
            }
            holder.tv_like.text = likesCount + " " + context.getString(R.string.likes)
            holder.tv_comment.text = commentsCount + " " + context.getString(R.string.comments)
            setGlideImage(holder.iv_user, articlesList[position].user!![0].avatar)

            if (articlesList[position].media!![0].image != null && articlesList[position].media!![0].image != "")
                setGlideImage(holder.iv_article, articlesList[position].media!![0].image)
            else
                holder.iv_article.visibility = View.GONE
        }
    }

    /**
     * This method is used for setting the Image using Glide.
     */
    private fun setGlideImage(view: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .error(R.mipmap.user_icon)
            .into(view)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_user: CircleImageView
        var tv_UserName: TextView
        var tv_Description: TextView
        var iv_article: ImageView
        var tv_articleContent: TextView
        var tv_articleTitle: TextView
        var tv_articleUrl: TextView
        var tv_like: TextView
        var tv_comment: TextView

        init {
            iv_user = itemView.findViewById(R.id.iv_user)
            tv_UserName = itemView.findViewById(R.id.tv_userName)
            tv_Description = itemView.findViewById(R.id.tv_description)
            iv_article = itemView.findViewById(R.id.iv_article)
            tv_articleContent = itemView.findViewById(R.id.tv_articleContent)
            tv_articleTitle = itemView.findViewById(R.id.tv_articleTitle)
            tv_articleUrl = itemView.findViewById(R.id.tv_articleUrl)
            tv_like = itemView.findViewById(R.id.tv_like)
            tv_comment = itemView.findViewById(R.id.tv_comment)
        }
    }
}