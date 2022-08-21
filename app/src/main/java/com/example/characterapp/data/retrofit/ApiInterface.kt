package com.example.characterapp.data.retrofit

import android.telecom.Call
import com.example.characterapp.data.mode.CharactersResponse


interface ApiInterface {

    @GET("character")
    fun getAllCharacters(): Call<CharactersResponse>

    @GET("character/{id}")
    fun getOneCharacter(@Path("id") id: String): Call<Character>

}