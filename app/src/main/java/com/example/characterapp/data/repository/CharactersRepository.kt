package com.example.characterapp.data.repository

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.characterapp.data.mode.CharactersResponse
import com.example.characterapp.data.retrofit.ApiInterface
import com.example.characterapp.utils.Resource
import javax.inject.Inject
import javax.security.auth.callback.Callback

class CharactersRepository @Inject constructor(private val apiInterface: ApiInterface) {



    private val charactersResponse = MutableLiveData<Resource<CharactersResponse>>()

    fun getCharacters() {
     charactersResponse.postValue(Resource.loading())

    val call: Call<CharactersResponse> = apiInterface.getAllCharacters()

     call.enqueue(object : Callback<CharactersResponse>{

      override fun onResponse(
        call: Call<CharactersResponse>,
         response: Response<CharactersResponse>
            ) {

         if (response.body() != null){
charactersResponse.postValue(Resource.success(response.body()!!))

           }else {
                    charactersResponse.postValue(Resource.error("Network Error"))
                }

            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                charactersResponse.postValue(Resource.error("Network Error: ${t.message}"))
            }

        })


    }

    fun getCharactersResponse(): LiveData<Resource<CharactersResponse>> = charactersResponse

    private val characterResponse = MutableLiveData<Resource<Character>>()

    fun getOneCharacter(id: String) {

        characterResponse.postValue(Resource.loading())

        val call: Call<Character> = apiInterface.getOneCharacter(id)

        call.enqueue(object : Callback<Character>{

            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {

                if (response.body() != null){

                    characterResponse.postValue(Resource.success(response.body()!!))

                }else {
                    characterResponse.postValue(Resource.error("Network Error"))
                }

            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                characterResponse.postValue(Resource.error("Network Error: ${t.message}"))
            }

        })


    }

    fun getCharacterResponse(): LiveData<Resource<Character>> = characterResponse
    //endregion

}