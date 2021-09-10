package com.example.emptyviewbinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emptyviewbinding.databinding.MainFragmentBinding
import com.example.emptyviewbinding.preference.PreferenceActivity


class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val mBinding get() = _binding!!
    lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: ItemsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        FITLER = prefs.getString("prefFilter", "").toString()
        TYPE_DATABASE = when(prefs.getBoolean("database", false)) {
            false -> TYPE_CURSOR
            true -> TYPE_ROOM
        }
Log.i("123", "DATABASE: $TYPE_DATABASE")
        Log.i("123", "FILTER:${FITLER}")
        mAdapter = ItemsAdapter()

        GridLayoutManager(
            requireContext(), // context
            3, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            mBinding.recycler.layoutManager = this
        }
        mBinding.recycler.adapter = mAdapter
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            mViewModel.readAllData.observe(viewLifecycleOwner, Observer { list ->
                mAdapter.submitList(list)

            })
            mBinding.fabAdd.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_addFragment)
            }


        return mBinding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.main_menu){
            val intent = Intent(requireActivity(), PreferenceActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
            mViewModel.readAllData.removeObservers(viewLifecycleOwner)
        mBinding.recycler.adapter = null
        _binding = null
    }


}

