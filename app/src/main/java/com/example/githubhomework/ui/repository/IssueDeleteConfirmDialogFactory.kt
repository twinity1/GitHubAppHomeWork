package com.example.githubhomework.ui.repository

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.example.githubhomework.R

class IssueDeleteConfirmDialogFactory {
    fun create(context: Context, completionHandler: (Boolean) -> Unit): AlertDialog
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)

        builder.setTitle(context.getString(R.string.delete_dialog_title))
        builder.setMessage(context.getString(R.string.delete_dialog_message))

        builder.setPositiveButton(context.getString(R.string.delete_dialog_yes),
            DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
                completionHandler(false)
            })
        builder.setNegativeButton(context.getString(R.string.delete_dialog_no),
            DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
                    completionHandler(true)
            })

        val dialog: AlertDialog = builder.create()

        return dialog
    }
}