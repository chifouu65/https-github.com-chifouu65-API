package fr.noah.api.utils.reflexion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class Reflexion {

    public static int getPing(Player player) {
        try {
            String bukkitVersion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitVersion + ".entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle").invoke(player);
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping").get(handle);
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException |
                 IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException |
                 NoSuchFieldException e) {
            return -1;
        }
    }

    public static void playerSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Class<?> soundClass = getClass("org.bukkit.sound");
            Sound sound = (Sound) Objects.requireNonNull(soundClass).getField(soundName).get(null);
            Method playSoundMethod = getMethod(player.getClass(), "playSound", Location.class, Sound.class, float.class, float.class);
            playSoundMethod.invoke(player, location, sound, volume, pitch);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameters) throws NoSuchMethodException {
        Class<?>[] primitiveType = DataType.getPrimitive(parameters);
        for (Method method : clazz.getMethods()) {
            if (!method.getName().equals(methodName) || !DataType.compare(DataType.getPrimitive(method.getParameterTypes()), primitiveType)) {
                continue; // arrete le tour de boucle actuel pour commencer le suivant
            }
            return method;
        }
        throw new NoSuchMethodException("There is no such method in this class with the specied name end parameter types");
    }

}


