package com.example.emptyviewbinding.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.data.Person
import com.example.emptyviewbinding.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var mViewModel: AddViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private var isWhite:Int = 0


    @SuppressLint("ShowToast", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this).get(AddViewModel::class.java)



        binding.apply {
            switchSkin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isWhite = 1
                    binding.switchSkin.text = "Master"
                } else {
                    isWhite = 0
                    binding.switchSkin.text = "Slave"
                }
            }
            addBtn.setOnClickListener { insertDataToDatabase() }
        }
        return binding.root
    }

    private fun inputCheck(name: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || age.toInt() !in 0..130)
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun insertDataToDatabase() {

        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString()
        val skin = isWhite
        // Create User Object
        if (inputCheck(name, age)) {
            val note = Person(
                0,
                name,
                age.toInt(),
                skin
            )
            // Add Data to Database
            mViewModel.insert(note)
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }
        else
            Toast.makeText(requireContext(), "Check correct data", Toast.LENGTH_SHORT).show()


    }
}






