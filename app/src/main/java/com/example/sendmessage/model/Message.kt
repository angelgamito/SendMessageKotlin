package com.example.sendmessage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Message(
    val id: Int,
    val content: String,
    val sender: Person,
    val receiver: Person
) : Parcelable