package com.example.emptyviewbinding.update

import android.app.ActionBar
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emptyviewbinding.MainViewModel
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.data.NbaPlayer
import com.example.emptyviewbinding.databinding.FragmentUpdateBinding
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: MainViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var isWhite: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)




        mUserViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // typeChooser()
        binding.apply {
            etName.setText(args.item.name)
            etAge.setText(args.item.age.toString())
            switchSkin.text = if (args.item.skin == 0) "Negr" else "White"
            isWhite =args.item.skin
            switchSkin.isChecked = args.item.skin == 1
            switchSkin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    isWhite = 1
                    binding.switchSkin.text = "White"
                } else {
                    isWhite = 0
                    binding.switchSkin.text = "Negr"
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
        val age = binding.etAge.text.toString().toInt()
        val skin = isWhite


        // Create  Object
        val note = NbaPlayer(
            args.item.id, name,
            age, skin,
        )
        // Update Current
        mUserViewModel.update(note)
        Log.i("123", "SKIN ${skin.toString()}")
        // Navigate Back
        findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
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

private fun deleteUser() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setPositiveButton("Yes") { _, _ ->
        mUserViewModel.delete(args.item)
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


//
//    private fun typeChooser() {
//
//        val types = resources.getStringArray(R.array.types)
//        val adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_spinner_item, types
//        )
//        binding.spinner.adapter = adapter
//        binding.spinner.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>,
//                view: View, position: Int, id: Long
//            ) {
//                Toast.makeText(
//                    requireContext(),
//                    getString(R.string.selected_item) + " " +
//                            "" + types[position], Toast.LENGTH_SHORT
//                ).show()
//                type = position
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                type = 0
//            }
//
//        }
//    }
//
//    private fun dateChooser() {
//        var selectedDate = ""
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//
//        val dpd = DatePickerDialog(requireContext(), { view, sYear, sMonth, sDayOfMonth ->
//            selectedDate = "$sDayOfMonth.${sMonth + 1}.$sYear"
//
//            binding.addDateTv.text = selectedDate
//        }, year, month, day)
//        dpd.show()
//
//    }
//
//
//
//    private fun inputCheck(client: String, date: String): Boolean {
//        return !(TextUtils.isEmpty(client) && TextUtils.isEmpty(date))
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.menu_delete) {
//            deleteUser()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun deleteUser() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("Yes") { _, _ ->
//            mUserViewModel.deleteUser(args.currentUser)
//            Toast.makeText(
//                requireContext(),
//                "Successfully removed: ${args.currentUser.client}",
//                Toast.LENGTH_SHORT
//            ).show()
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//        }
//        builder.setNegativeButton("No") { _, _ -> }
//        builder.setTitle("Delete ${args.currentUser.client}?")
//        builder.setMessage("Are you sure you want to delete ${args.currentUser.client}?")
//        builder.create().show()
//    }
