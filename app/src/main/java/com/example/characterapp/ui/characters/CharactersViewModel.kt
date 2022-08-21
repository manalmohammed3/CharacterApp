package com.example.characterapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.characterapp.data.mode.CharactersResponse
import com.example.characterapp.data.repository.CharactersRepository
import com.example.characterapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val response: LiveData<Resource<CharactersResponse>> =
        charactersRepository.getCharactersResponse()

    init {
        getCharacters()
    }


    fun getCharacters(){
        charactersRepository.getCharacters()
    }

    fun getCharactersResponse(): LiveData<Resource<CharactersResponse>> = response


}