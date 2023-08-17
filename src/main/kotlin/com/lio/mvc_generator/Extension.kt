package com.lio.mvc_generator

fun String.camelCase(): String {
    return if (this.length > 1) this.substring(0, 1).uppercase() + this.substring(1) else this
}

fun String.underlineToCamelCase(): String {
    val str = this.trim()
    if (str.isEmpty()) return ""
    return str.split("_").joinToString("") {
        if (it[0] in 'a'..'z') it[0] - 32 + it.substring(1) else it
    }
}

fun String.camelToUnderlineCase(): String {
    val str = this.trim()
    if (str.isEmpty()) return ""
    val list = mutableListOf<String>()
    var i = 1
    var j = 0
    while (i < str.length) {
        if (str[i] in 'A'..'Z') {
            list.add(str.substring(j, i))
            j = i
        }
        i++
    }
    list.add(str.substring(j))
    return list.joinToString("_") { it.lowercase() }
}