package com.example.networkexample.data.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
class ListResponseApiModel() {
    @set:ElementList(required = false, name = "Valute", inline = true)
    @get:ElementList(required = false, name = "Valute", inline = true)
    var valuteResponses: List<SimpleValuteApiModel>? = null
}