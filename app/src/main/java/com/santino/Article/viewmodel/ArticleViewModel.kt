package com.santino.Article.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santino.Article.model.ArticlePojo
import com.santino.Article.networking.Repository

class ArticleViewModel :ViewModel() {
    var mutableLiveData: MutableLiveData<ArrayList<ArticlePojo>> ?= null
    var repository: Repository?= null

    fun init(pageNum: Int) {
        if (mutableLiveData != null) {
            return
        }
        repository = Repository.instance
        mutableLiveData = repository!!.getArticles(pageNum,10)
    }

    fun getRepository(): LiveData<ArrayList<ArticlePojo>>? {
        return mutableLiveData
    }
}