package com.vladimirpandurov.ecommercespringboot.config;


import com.vladimirpandurov.ecommercespringboot.entity.Product;
import com.vladimirpandurov.ecommercespringboot.entity.ProductCategory;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@Data
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private  EntityManager entityManager;



    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        //disable HTTP methods for Product: PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))));

        //disable HTTP methods for ProductCategory: PUT, POST, DELETE
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))));

        //call an internal helper method
        exposeIds(config);

    }

    private void exposeIds(RepositoryRestConfiguration config){
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
