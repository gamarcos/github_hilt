package br.com.grabrielmarcos.githubhilt.feature.repositories.extetions

import android.view.View

fun View.show() { this.visibility = View.VISIBLE }

fun View.hide() { this.visibility = View.GONE }

fun View.inv() { this.visibility = View.INVISIBLE }