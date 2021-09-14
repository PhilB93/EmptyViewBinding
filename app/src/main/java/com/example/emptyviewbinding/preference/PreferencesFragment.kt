package com.example.emptyviewbinding.preference

import android.os.Bundle
import android.view.*
import androidx.preference.PreferenceFragmentCompat
import com.example.emptyviewbinding.R

class PreferencesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pref_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnApply -> activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}