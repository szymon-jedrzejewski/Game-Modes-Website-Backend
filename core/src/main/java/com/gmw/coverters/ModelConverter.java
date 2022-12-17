package com.gmw.coverters;

public interface ModelConverter<T,U> {

    T convertToTO(U u);
}
