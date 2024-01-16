package com.example.easyfoods.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfoods.pojo.Meal
import com.example.easyfoods.pojo.MealList
import com.example.easyfoods.retrofit.Retrofitinstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private  var randomMealLiveData = MutableLiveData<Meal>()
    fun getRandomMeal(){
        Retrofitinstance.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HOMEFRAGMENT", t.message.toString())
            }
        })
    }

    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
}