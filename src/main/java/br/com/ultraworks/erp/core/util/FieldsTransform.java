package br.com.ultraworks.erp.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.ultraworks.erp.core.annotation.IgnoreUppercaseTransform;
import br.com.ultraworks.erp.core.entity.UWEntityBase;

public class FieldsTransform {

	public static void transform(UWEntityBase entity) {
        Class<? extends UWEntityBase> clazz = entity.getClass();
        List<Field> fields = new ArrayList<>();
        Class<?> superClazz = clazz;
        while (!superClazz.equals(UWEntityBase.class)) {
            fields.addAll(Arrays.asList(superClazz.getDeclaredFields()));
            superClazz = superClazz.getSuperclass();
        }
        fields.forEach(field -> transformField(entity, field));
    }

    private static void transformField(UWEntityBase entity, Field field) {
        if (field.getType().equals(String.class)) {
        	transformString(entity, field);
        }
    }

	private static void transformString(UWEntityBase entity, Field field) {
		// Check for the @IgnoreUppercaseTransform annotation
        if (field.isAnnotationPresent(IgnoreUppercaseTransform.class)) {
            return;
        }
        try {
            String fieldName = field.getName();
            String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Class<?> entityClass = entity.getClass();
            Method getterMethod = entityClass.getMethod(getterMethodName);
            String entityFieldValue = (String) getterMethod.invoke(entity);
            
            if (entityFieldValue != null) {
                entityFieldValue = entityFieldValue.toUpperCase();
                
                String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method setterMethod = entityClass.getMethod(setterMethodName, String.class);
                setterMethod.invoke(entity, entityFieldValue);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
