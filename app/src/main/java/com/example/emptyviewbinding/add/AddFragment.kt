package com.example.emptyviewbinding.add

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.emptyviewbinding.MainViewModel
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.data.NbaPlayer
import com.example.emptyviewbinding.databinding.FragmentAddBinding
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: MainViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private var isWhite:Int = 0


    @SuppressLint("ShowToast", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(MainViewModel::class.java)



        binding.apply {
            switchSkin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isWhite = 1
                    binding.switchSkin.text = "White"
                } else {
                    isWhite = 0
                    binding.switchSkin.text = "Negr"
                }
            }

            addBtn.setOnClickListener { insertDataToDatabase() }
        }



        return binding.root
    }


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun insertDataToDatabase() {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString().toInt()
        val skin = isWhite
        // Create User Object
        val note = NbaPlayer(
            0,
            name,
            age,
            skin

        )

        // Add Data to Database
        mUserViewModel.insert(note)
        // Navigate Back
        findNavController().navigate(R.id.action_addFragment_to_mainFragment)
    }
}






