package com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.deepanshunamdeo.postscrollingapp.data.model.User
import com.android.deepanshunamdeo.postscrollingapp.data.repository.MainRepository
import com.android.deepanshunamdeo.postscrollingapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.w3c.dom.Comment

class MainViewModel {
    class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

        private val users = MutableLiveData<Resource<List<User>>>()
        private val comments = MutableLiveData<Resource<List<Comment>>>()
        private val compositeDisposable = CompositeDisposable()

        init {
            fetchUsers()

        }

        private fun fetchUsers() {
            users.postValue(Resource.loading(null))
            compositeDisposable.add(
                mainRepository.getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ userList ->
                        users.postValue(Resource.success(userList))
                    }, { throwable ->
                        users.postValue(Resource.error("Something Went Wrong", null))
                    })
            )
        }

        override fun onCleared() {
            super.onCleared()
            compositeDisposable.dispose()
        }

        fun getUsers(): LiveData<Resource<List<User>>> {
            return users
        }
        fun getComments(): LiveData<Resource<List<Comment>>> {
            return comments
        }

    }
}