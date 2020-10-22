package com.prateek.android.retrofit_rxjava_mvvm.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class User {
    @SerializedName("username")
    @Expose
     val street: String? = null

    @SerializedName("suite")
    @Expose
     val suite: String? = null

    @SerializedName("zipcode")
    @Expose
     val zipcode: String? = null

}