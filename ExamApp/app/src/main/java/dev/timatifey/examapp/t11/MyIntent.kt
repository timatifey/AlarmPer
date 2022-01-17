package dev.timatifey.examapp.t11

import java.net.URI

data class MyIntent(
    var action: String? = null,
    val categories: MutableSet<String> = mutableSetOf(),
    var data: URI? = null,
    var type: String? = null,
    var flags: Int = 0,
    val extras: MutableMap<String, Any> = mutableMapOf(),
)