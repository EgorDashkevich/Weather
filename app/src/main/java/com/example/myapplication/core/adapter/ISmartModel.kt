package com.example.myapplication.core.adapter

interface ISmartModel {
    fun areItemsTheSame(other: Any): Boolean
    fun isContentsTheSame(other: Any): Boolean {
        return other == this
    }

    fun getChangePayload(other: Any): Boolean {
        return other == this
    }
}