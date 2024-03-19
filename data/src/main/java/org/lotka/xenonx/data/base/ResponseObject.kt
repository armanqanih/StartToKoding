package org.lotka.xenonx.data.base

interface ResponseObject<out DomainObject : Any?> {
    fun toDomain(): DomainObject
}

