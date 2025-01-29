package com.ivan.nutriapp.domain

data class Quantity(
    val amount: Float,
    val unit: QuantityUnit
)

enum class QuantityUnit {

    GRAM,
    MICRO_GRAM,
    IU,
    KCAL
}
