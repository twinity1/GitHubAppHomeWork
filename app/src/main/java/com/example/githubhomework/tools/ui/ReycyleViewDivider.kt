package com.example.githubhomework.tools.ui

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.githubhomework.R

fun RecyclerView.addDivider(context: Context) {
    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.item_separator)!!)
    this.addItemDecoration(divider)
}