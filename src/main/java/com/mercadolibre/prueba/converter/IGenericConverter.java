package com.mercadolibre.prueba.converter;

public interface IGenericConverter<E,D> {
	public E fromEntity(D dto);
	public D fromDto(E entity);

}
