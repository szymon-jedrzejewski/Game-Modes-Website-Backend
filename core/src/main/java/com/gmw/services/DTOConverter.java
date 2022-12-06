package com.gmw.services;

public interface DTOConverter<T,U> {

    T convert(U u);
}
