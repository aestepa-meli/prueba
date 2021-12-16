package com.mercadolibre.prueba.converter;
/**
 * <p>IGenericConverter</p>
 * Interfaz para generacion de converters
 * @author Jhon
 *
 * @param <E> parametro generico Entidad
 * @param <D> parametro generido DTO
 */
public interface IGenericConverter<E,D> {
	/**
	 * 
	 * @param dto
	 * @return la entidad asociada
	 */
	public E fromEntity(D dto);
	/**
	 * 
	 * @param entity
	 * @return el dto asociado
	 */
	public D fromDto(E entity);

}
