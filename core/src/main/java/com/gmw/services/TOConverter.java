package com.gmw.services;

public interface TOConverter<T,U> {

    T convert(U u);
}
