package com.tank_service.Mapper;

public interface IMapper<I, O> {
    public O map(I in);
}
