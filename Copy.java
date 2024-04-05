    public static <T> T copyObject(T original) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = original.getClass();
        T copy = (T) clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // Ensure we can access the field
            field.setAccessible(true);
            // Skip static or final fields
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            // Get the value of the field from the original object
            Object value = field.get(original);
            // Set the value of the field in the copy object
            field.set(copy, value);
        }

        return copy;
    }
