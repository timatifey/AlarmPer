package dev.timatifey.examapp.t11

data class MyIntentFilter(
    val actions: MutableSet<String> = mutableSetOf(),
    val categories: MutableSet<String> = mutableSetOf(),
    var scheme: String? = null,
    var host: String? = null,
    var port: String? = null,
    var path: String? = null,
    var type: String? = null,
)