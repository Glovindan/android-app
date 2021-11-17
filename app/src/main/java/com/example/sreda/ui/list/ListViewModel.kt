package com.example.sreda.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sreda.api.RetrofitProvider
import com.example.sreda.data.Response
import com.example.sreda.data.User
import com.example.sreda.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback

/**
 * @author s.timirov
 */
class ListViewModel : BaseViewModel() {

    private val mutableUserList: MutableLiveData<List<User>> = MutableLiveData()
    val userList: LiveData<List<User>> = mutableUserList

    private val mutableError: MutableLiveData<String?> = MutableLiveData()
    val errorLiveData: LiveData<String?> = mutableError

    fun loadUsers(page: Int) {
        RetrofitProvider.userService
            .getUsers(page)
            .enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    val body = response.body()
                    if (body != null) {
                        val list = mutableListOf<User>()
                        repeat(20) {
                            list.addAll(body.users)
                        }
                        mutableUserList.postValue(list)
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    mutableError.postValue(t.message)
                }
            })
    }
}
