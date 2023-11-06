package com.akashdas.recipesearchapp.Fragment

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.akashdas.recipesearchapp.Helper.Singleton
import com.akashdas.recipesearchapp.R
import com.akashdas.recipesearchapp.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // all about chip
        chip()

    }

    private fun chip() {
        binding.chipGroup.isSingleSelection = true

        Singleton.chipTexts.forEach { text ->
            val chip = Chip(context).apply {
                this.text = text
                isCheckable = true
                // Define color in your colors.xml
                chipStrokeColor = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.chip_color))
            }
            binding.chipGroup.addView(chip)
        }

        // select the first chip by default
        (binding.chipGroup.getChildAt(0) as? Chip)?.isChecked = true

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            // The 'checkedId' is the ID of the selected chip.
            val chip = group.findViewById<Chip>(checkedId)
            chip?.let {
                val selectedText: String = it.text.toString()

                binding.editTextText.setText(selectedText)

                searchData(selectedText)
            }

        }

    }

    private fun searchData(selectedText: String) {

    }
}