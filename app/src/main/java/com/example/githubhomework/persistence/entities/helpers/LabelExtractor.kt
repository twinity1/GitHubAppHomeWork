package com.example.githubhomework.persistence.entities.helpers

import com.example.githubhomework.persistence.entities.Issue
import com.example.githubhomework.persistence.entities.Label

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