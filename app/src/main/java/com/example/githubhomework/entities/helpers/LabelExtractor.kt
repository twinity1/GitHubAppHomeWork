package com.example.githubhomework.entities.helpers

import com.example.githubhomework.entities.Issue
import com.example.githubhomework.entities.Label

class LabelExtractor {
    companion object {
        fun extractFromIssueList(issues: List<Issue>): List<Label> {
            val labelHash = HashMap<Int, Label>()

            issues.forEach {
                it.labels.forEach {
                    labelHash[it.id] = it
                }
            }

            return ArrayList(labelHash.values);
        }
    }
}