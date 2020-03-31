package com.example.githubhomework.ui.repository

import android.graphics.Color
import androidx.lifecycle.Observer
import com.example.githubhomework.R
import com.example.githubhomework.persistence.entities.Label
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_repository.*

class LabelObserver {
    fun create(owner: RepositoryFragment): Observer<List<Label>> {
        return Observer {
            owner.filterChipGroup.removeAllViews()

            val selectedLabels = HashMap<Label, Boolean>()

            it.forEach {
                val chip = Chip(owner.requireContext(), null, R.style.Widget_MaterialComponents_Chip_Filter)

                chip.text = it.name
                chip.isCheckable = true
                chip.isEnabled = true
                chip.setChipBackgroundColorResource(R.color.colorPrimaryLight)
                chip.setTextColor(Color.WHITE)

                selectedLabels.put(it, false)

                val label = it

                chip.setOnClickListener {
                    val isSelected = selectedLabels[label]!!

                    selectedLabels[label] = !isSelected
                    owner.viewModel.filterIssues()
                }

                owner.filterChipGroup.addView(chip)
            }

            owner.viewModel.selectedLabels.value = selectedLabels
        }
    }
}