package Moon.mods;

import java.lang.reflect.Field;

public class ReflectionHelper {
    public static Field findField(Class<?> clazz, String... fieldNames) {
        Exception failed = null;
        int var4 = fieldNames.length;
        int var5 = 0;

        while (var5 < var4) {
            String fieldName = fieldNames[var5];

            try {
                Field f = clazz.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } catch (Exception var7) {
                failed = var7;
                ++var5;
            }
        }

        throw new ReflectionHelper.UnableToFindFieldException(failed);
    }

    public static Class<? super Object> getClass(ClassLoader loader, String... classNames) {
        Exception err = null;
        int var4 = classNames.length;
        int var5 = 0;

        while (var5 < var4) {
            String className = classNames[var5];

            try {
                return (Class<? super Object>) Class.forName(className, false, loader);
            } catch (Exception var7) {
                err = var7;
                ++var5;
            }
        }

        throw new ReflectionHelper.UnableToFindClassException(err);
    }

    public static class UnableToFindClassException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UnableToFindClassException(Exception err) {
            super(err);
        }
    }

    public static class UnableToFindFieldException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UnableToFindFieldException(Exception e) {
            super(e);
        }
    }

}
