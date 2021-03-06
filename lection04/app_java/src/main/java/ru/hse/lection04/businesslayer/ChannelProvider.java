package ru.hse.lection04.businesslayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Провайдер для работы с каналами
 */
public class ChannelProvider {
    /**
     * Инициализация каналов для уведомлений начиная с Android O.
     * @param context Context для обращения к NotificationManager
     */
    public void initializeChannels(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            checkAndCreateChannels(context);
        }

    }

    /**
     * Код для создания каналов, работает начиная с Android O.
     * @param context Context для обращения к NotificationManager
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void checkAndCreateChannels(Context context) {
        // Получаем NotificationManager ищ контектса
        final NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Перебираем все наши каналы из enum-а
        for (Channels info: Channels.values()) {
            // Пробуем достать канал из NotificationManager
            NotificationChannel channel = manager.getNotificationChannel(info.id);

            // Если его там нет, то создадим и добавим свой
            if (channel == null) {
                // Создаем новый канал
                final String name = context.getString(info.nameResId);
                channel = new NotificationChannel(
                        info.id
                        , name
                        , NotificationManager.IMPORTANCE_DEFAULT
                );
                channel.setLightColor(Color.BLUE);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);


                // Добавляем канал в NotificationManager
                manager.createNotificationChannel(channel);
            }
        }
    }
}
