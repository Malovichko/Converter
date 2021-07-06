package com.example.networkexample.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Valute", strict = false)
data class SimpleValuteApiModel @JvmOverloads constructor(

    @field:Element(required = false, name = "NumCode")
    var numCode: String = "",

    @field:Element(required = false, name = "CharCode")
    var charCode: String = "",

    @field:Element(required = false, name = "Nominal")
    var nominal: String = "",

    @field:Element(required = false, name = "Name")
    var name: String = "",

    @field:Element(required = true, name = "Value")
    var value: String = ""
)
