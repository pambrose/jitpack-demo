package com.pambrose

data class JitpackDemo(
    val name: String,
    val description: String,
) {
    override fun toString(): String = "name: $name, description: $description"
}
