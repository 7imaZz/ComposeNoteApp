package com.shorbgy.composenoteapp.feature_note.domain.util

sealed class OrderType{
    object Asc: OrderType()
    object Desc: OrderType()
}
