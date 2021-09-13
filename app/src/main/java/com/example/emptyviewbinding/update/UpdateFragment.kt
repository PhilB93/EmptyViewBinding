package com.example.emptyviewbinding.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.data.Person
import com.example.emptyviewbinding.databinding.FragmentUpdateBinding
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mViewModel: UpdateViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var isWhite: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)




        mViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        // typeChooser()
        binding.apply {
            etName.setText(args.item.name)
            etAge.setText(args.item.age.toString())
            switchSkin.text = if (args.item.skin == 0) "Slave" else "Master"
            isWhite = args.item.skin
            switchSkin.isChecked = args.item.skin == 1
            switchSkin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isWhite = 1
                    binding.switchSkin.text = "Master"
                } else {
                    isWhite = 0
                    binding.switchSkin.text = "Slave"
                }
            }
            addBtn.setOnClickListener {
                updateItem()
            }
            setHasOptionsMenu(true)

            return binding.root
        }
    }

    private fun updateItem() {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString()
        val skin = isWhite
        // Create  Object
        if (inputCheck(name, age)) {
            val note = Person(
                args.item.id, name,
                age.toInt(), skin,
            )
            // Update Current
            mViewModel.update(note)
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
            Log.i("123", "SKIN ${skin.toString()}")
        }
        // Navigate Back
        else
            Toast.makeText(requireContext(), "Check correct data", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun inputCheck(name: String, age: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(age)  || age.toInt() !in 1..130)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mViewModel.delete(args.item)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.item.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)


        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.item.name}?")
        builder.setMessage("Are you sure you want to delete ${args.item.name}?")
        builder.create().show()
    }


}
