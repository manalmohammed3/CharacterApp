package com.example.characterapp.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.characterapp.R
import com.example.characterapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


    @AndroidEntryPoint
    class CharactersFragment : Fragment() {

      private lateinit var binding: FragmentCharactersBinding

         private lateinit var adapter: CharactersAdapter

      private val viewModel: CharactersViewModel by viewModels()


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentCharactersBinding.inflate(inflater)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) { super.onViewCreated(view, savedInstanceState)

            adapter = CharactersAdapter(listOf())
            adapter.onCharacterClickListener = object : CharactersAdapter.CharacterClickListener {

                override fun onCharacterClick(id: Int, itemRoot: ConstraintLayout) {

                }

            }


            binding.apply {

                recyclerView.layoutManager = LinearLayoutManager(activity)

                val alphaAdapter = AlphaInAnimationAdapter(adapter)
                val scaleAdapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                    setDuration(500)
                    setFirstOnly(false)
                }

                recyclerView.adapter = scaleAdapter

            }


            viewModel.getCharactersResponse().observe(viewLifecycleOwner, { resource ->

                when(resource.status){

                    Resource.Status.LOADING ->{
                        binding.shimmerLayout.visibility = View.VISIBLE
                        binding.shimmerLayout.startShimmer()
                    }

                    Resource.Status.SUCCESS ->{
                        binding.shimmerLayout.visibility = View.GONE
                        binding.shimmerLayout.stopShimmer()

                        if (resource.data != null){
                            adapter.setItems(resource.data.results)
                        }


                    }
                    Resource.Status.ERROR ->{
                        binding.shimmerLayout.visibility = View.GONE
                        binding.shimmerLayout.stopShimmer()

                        Toast.makeText(activity, resource.message, Toast.LENGTH_LONG).show()
                    }


                }

            })

        }


    }