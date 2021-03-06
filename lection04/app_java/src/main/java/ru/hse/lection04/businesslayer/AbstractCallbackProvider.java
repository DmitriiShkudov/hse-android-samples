package ru.hse.lection04.businesslayer;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, которые содержит логику для работу с подписчиками
 * @param <LISTENER> любой подписчик
 */
public class AbstractCallbackProvider<LISTENER> {
    protected static final int EMPTY = 0;

    protected final Set<LISTENER> mListeners = new HashSet<>();


    /***
     * Зарегестрировать подписчика
     * @param listener подписчик
     */
    public void register(LISTENER listener) {
        final int lastSize = mListeners.size();

        mListeners.add(listener);

        if (lastSize == EMPTY) {
            setActivation(true);
        }
    }

    /**
     * Удалить подписчика
     * @param listener подписчик
     */
    public void unregister(LISTENER listener) {
        mListeners.remove(listener);

        if (mListeners.size() == EMPTY) {
            setActivation(false);
        }
    }

    /**
     * В зависимости от изменения набора подписчиков вызовется этот метод
     * @param value true - означает что появились слушатели и можно начать процесс отслеживания даных и уведомления. false - когда список подписчиков пуст
     */
    protected void setActivation(boolean value) {

    }
}
