package com.gmw.coverters;

public interface TOConverter<T,U> {

    U convertToModel(T t);
}
