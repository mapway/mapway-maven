package cn.mapway.ui.client.mqtt.event;


/**
 * The Class MessageArriveEvent.
 */
public class MessageArriveEvent extends BaseEvent {

    /**
     * Instantiates a new message arrive event.
     */
    protected MessageArriveEvent() {

    }

    /**
     * Duplicate.
     *
     * @return true, if successful
     */
    public final native boolean duplicate()/*-{
        return this.duplicate;
  }-*/;

    /**
     * Duplicate.
     *
     * @param v the v
     */
    public final native void duplicate(boolean v)/*-{
        this.duplicate = v;
  }-*/;

    /**
     * Retained.
     *
     * @return true, if successful
     */
    public final native boolean retained()/*-{
        return this.retained;
  }-*/;

    /**
     * Retained.
     *
     * @param v the v
     */
    public final native void retained(boolean v)/*-{
        this.retained = v;
  }-*/;


    /**
     * Topic.
     *
     * @return the string
     */
    public final native String topic()/*-{
        return this.destinationName;
  }-*/;

    /**
     * Topic.
     *
     * @param topic the topic
     */
    public final native void topic(String topic)/*-{
        this.destinationName = topic;
  }-*/;

    /**
     * Msg.
     *
     * @return the string
     */
    public final native String msg()/*-{
        return this.payloadString;
  }-*/;

    /**
     * Msg.
     *
     * @param msg the msg
     */
    public final native void msg(String msg)/*-{
        this.payloadString = msg;
  }-*/;


    /**
     * 0 Best effort (default). 1 At least once. 2 Exactly once.
     *
     * @return the int
     */
    public final native int qos()/*-{
        return this.qos;
  }-*/;

    /**
     * 0 Best effort (default). 1 At least once. 2 Exactly once.
     *
     * @param qos the qos
     */
    public final native void qos(int qos)/*-{
        this.qos = qos;
  }-*/;
}
