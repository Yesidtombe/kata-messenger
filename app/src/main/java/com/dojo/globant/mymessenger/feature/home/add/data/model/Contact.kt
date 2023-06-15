package com.dojo.globant.mymessenger.feature.home.add.data.model

data class Contact(
    val id: String,
    val name: String
) {
    var numbers = ArrayList<String>()

    constructor(): this("", "")
}