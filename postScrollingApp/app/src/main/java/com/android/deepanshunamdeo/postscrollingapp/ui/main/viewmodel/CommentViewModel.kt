package com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.repository.MainRepository
import com.android.deepanshunamdeo.postscrollingapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CommentViewModel (private val mainRepository: MainRepository): ViewModel() {


    private val comments = MutableLiveData<Resource<List<Comments>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchComments()
    }

    private fun fetchComments() {
        comments.postValue(Resource.loading(null))
        compositeDisposable.add(mainRepository.getComments()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ commentsList ->
                            comments.postValue(Resource.success(commentsList))
                        }, { throwable ->
                            comments.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getComments(): LiveData<Resource<List<Comments>>> {
        return comments
    }
}