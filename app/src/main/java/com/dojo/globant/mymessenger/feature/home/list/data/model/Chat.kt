package com.dojo.globant.mymessenger.feature.home.list.data.model

import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact

data class Chat(
    val id: String,
    val contact: Contact,
    val from: String
) {
    constructor(): this("", Contact(), "")
}
