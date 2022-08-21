package com.example.characterapp.utils

import android.provider.VoicemailContract

data class Resource <out T> (val status: VoicemailContract.Status, val data: T?, val message: String?){
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{


          fun <T> success(data: T): Resource<T> {
              return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message:String): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }



}
