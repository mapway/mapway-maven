package cn.mapway.ui.client.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 简单实现
 *
 * @author zhangjianshe
 */
public class ObservableImpl implements Observable {
  private List<Observer> observers;

  @Override
  public void addObserver(Observer obersver) {
    if (observers == null) {
      observers = new ArrayList();
    }
    observers.add(obersver);

  }

  @Override
  public void rmoveObserver(Observer observer) {
    if (observers != null) {
      observers.remove(observer);
    }
  }

    /**
     * Notify all the Observer of a change
     */
    public void notifyObservers() {
    notifyObservers(null);
  }

    /**
     * Notify all the Observer of a change along with an Hint Object
     *
     * @param hint Hint to the Observers as to what they should do.
     */
    public void notifyObservers(Object hint) {
    if (observers != null) {
      Iterator iter = this.observers.iterator();
      while (iter.hasNext()) {
        Observer observer = (Observer) iter.next();
        observer.update(this, hint);
      }
    }
  }
}
