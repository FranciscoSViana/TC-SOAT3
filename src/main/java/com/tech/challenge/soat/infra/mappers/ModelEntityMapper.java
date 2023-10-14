package com.tech.challenge.soat.infra.mappers;

import com.tech.challenge.soat.core.utils.CustomMapper;
import jakarta.persistence.Entity;
import org.springframework.core.annotation.AnnotatedElementUtils;

public interface ModelEntityMapper <M, E>{
    CustomMapper mapper = CustomMapper.getInstance();

    Class<E> getEntityClass();
    Class<M> getModelClass();

    default E toEntity(M model){
        if(!hasEntityAnnotation()){
            throw new IllegalArgumentException("Entity does not have a @Entity annotation declared");
        }
        return  mapper.map(model, getEntityClass());
    }

    default M toModel(E entity){
        if(!hasEntityAnnotation()){
            throw new IllegalArgumentException("Entity does not have a @Entity annotation declared");
        }
        return mapper.map(entity, getModelClass());
    }

    private boolean hasEntityAnnotation(){
        Class<?> clazz = getEntityClass();
        return AnnotatedElementUtils.hasAnnotation(clazz, Entity.class);
    }
}
