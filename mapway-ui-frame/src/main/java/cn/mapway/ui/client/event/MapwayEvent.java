package cn.mapway.ui.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * MapwayEvent.
 *
 * @param <T> the generic type
 */
public class MapwayEvent<T> extends GwtEvent<MapwayHandler<T>> {

	  /**
	   * Handler type.
	   */
	  private static Type<MapwayHandler<?>> TYPE;

    /**
     * Fires a selection event on all registered handlers in the handler
     * manager.If no such handlers exist, this method will do nothing.
     *
     * @param <T>     the selected item type
     * @param source  the source of the handlers
     * @param msgType the msg type
     * @param data    the data
     */
    public static <T> void fire(HasMapwayHandlers<T> source,Integer msgType, T data) {
	    if (TYPE != null) {
	      MapwayEvent<T> event = new MapwayEvent<T>(msgType,data);
	      source.fireEvent(event);
	    }
	  }

    /**
     * Gets the type associated with this event.
     *
     * @return returns the handler type
     */
    public static Type<MapwayHandler<?>> getType() {
	    if (TYPE == null) {
	      TYPE = new Type<MapwayHandler<?>>();
	    }
	    return TYPE;
	  }

	  /** The data. */
  	private final T data;
	  
  	/** The msg type. */
  	private Integer msgType;

    /**
     * Instantiates a new mapway event.
     *
     * @param msgType the msg type
     * @param data    the data
     */
    protected MapwayEvent(Integer msgType,T data) {
	    this.data = data;
	    this.msgType=msgType;
	  }

	  // The instance knows its BeforeMapwayHandler is of type I, but the TYPE
	  /* (non-Javadoc)
  	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
  	 */
  	// field itself does not, so we have to do an unsafe cast here.
	  @SuppressWarnings("unchecked")
	  @Override
	  public final Type<MapwayHandler<T>> getAssociatedType() {
	    return (Type) TYPE;
	  }

    /**
     * Gets the selected item.
     *
     * @return the selected item
     */
    public T getSelectedItem() {
	    return data;
	  }

	  /* (non-Javadoc)
  	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
  	 */
  	@Override
	  protected void dispatch(MapwayHandler<T> handler) {
	    handler.handle(this.getSource(),this.msgType,this.data);
	  }


    /**
     * The Constant CLOSE_AND_REFRESH.
     */
    public static final Integer CLOSE_AND_REFRESH = 1;

    /**
     * The Constant CLOSE.
     */
    public static final Integer CLOSE = 2;

    /**
     * The Constant CANCEL.
     */
    public static final Integer CANCEL = 3;

    /**
     * The Constant CHILDCLICK.
     */
    public static final Integer CHILDCLICK = 4;

    /**
     * The Constant CREATEROOT.
     */
    public static final Integer CREATEROOT = 5;

    /**
     * The Constant ITEMCLICK.
     */
    public static final Integer ITEMCLICK = 6;

    /**
     * The Constant SELECTUSER.
     */
    public static final Integer SELECTUSER = 7;

    /**
     * The Constant CONTEXTMENU.
     */
    public static final Integer CONTEXTMENU = 8;

    /**
     * The Constant DELETE.
     */
    public static final Integer DELETE = 9;

    /**
     * The Constant EDIT.
     */
    public static final Integer EDIT = 10;

    /**
     * The Constant UPDATE.
     */
    public static final Integer UPDATE = 11;

    /**
     * The Constant REFRESH.
     */
    public static final Integer REFRESH = 12;

    /**
     * The Constant TRANSFROM.
     */
    public static final Integer TRANSFROM = 13;

    /**
     * The Constant OK.
     */
    public static final Integer OK = 14;

    /**
     * The Constant EDITCONTENT.
     */
    public static final Integer EDITCONTENT = 15;

    /**
     * The Constant PAGE.
     */
    public static final Integer PAGE = 16;

    /**
     * The Constant NEXT.
     */
    public static final Integer NEXT = 17;

    /**
     * The Constant PREV.
     */
    public static final Integer PREV = 18;

    /**
     * The Constant FIRST.
     */
    public static final Integer FIRST = 19;

    /**
     * The Constant LAST.
     */
    public static final Integer LAST = 20;

    /**
     * The Constant SYLLABUS_CONTENT_EDIT.
     */
    public static final Integer SYLLABUS_CONTENT_EDIT = 21;

    /**
     * The Constant EDIT_SECTION.
     */
    public static final Integer EDIT_SECTION = 22;

    /**
     * The Constant SELECT.
     */
    public static final Integer SELECT = 23;

    /**
     * The Constant SAVE_TO_WEB.
     */
    public static final Integer SAVE_TO_WEB = 24;

    /**
     * The Constant PREVIEW.
     */
    public static final Integer PREVIEW = 25;

    /**
     * The Constant VALUECHANGE.
     */
    public static final Integer VALUECHANGE = 26;

    /**
     * The Constant UP.
     */
    public static final Integer UP = 27;

    /**
     * The Constant DOWN.
     */
    public static final Integer DOWN = 28;

    /**
     * The Constant SAVE_TO_TEMPLATE.
     */
    public static final Integer SAVE_TO_TEMPLATE = 29;

    /**
     * The Constant MESSAGE.
     */
    public static final Integer MESSAGE = 30;

    /**
     * The Constant SUBMIT.
     */
    public static final Integer SUBMIT = 31;

    /**
     * The Constant RESRTRICT.
     */
    public static final Integer RESRTRICT = 32;

    /**
     * The Constant NAVI.
     */
    public static final Integer NAVI = 33;

    /**
     * The Constant INFO.
     */
    public static final Integer INFO = 34;

    /**
     * The Constant DOING.
     */
    public static final Integer DOING = 35;

    /**
     * The Constant CHECK_ERROR.
     */
    public static final Integer CHECK_ERROR = 36;

    /**
     * The Constant SAVE.
     */
    public static final Integer SAVE = 37;

    /**
     * The Constant CHANGE_PWD.
     */
    public static final Integer CHANGE_PWD = 38;

    /**
     * The Constant CREATE.
     */
    public static final Integer CREATE = 39;

    /**
     * The Constant SUCCESS.
     */
    public static final Integer SUCCESS = 40;

    /**
     * The Constant FAIL.
     */
    public static final Integer FAIL = 41;

    /**
     * The Constant DETAIL.
     */
    public static final Integer DETAIL = 42;

    /**
     * The Constant BIND_PRODUCT.
     */
    public static final Integer BIND_PRODUCT = 43;

    /**
     * The Constant BIND_CLASSROOM.
     */
    public static final Integer BIND_CLASSROOM = 44;

    /**
     * The Constant LOGIN.
     */
    public static final Integer LOGIN = 45;

    /**
     * The Constant RETURN.
     */
    public static final Integer RETURN = 46;

    /**
     * The Constant COLOR.
     */
    public static final Integer COLOR = 47;

    /**
     * The Constant COLORS.
     */
    public static final Integer COLORS = 48;

    /**
     * The Constant QUIT.
     */
    public static final Integer QUIT = 49;

    /**
     * The Constant ENABLED.
     */
    public static final Integer ENABLED = 50;

    /**
     * The Constant SEARCH.
     */
    public static final Integer SEARCH = 51;

    /**
     * The Constant DBCLICK.
     */
    public static final Integer DBCLICK = 52;

    /**
     * The Constant MODIFY.
     */
    public static final Integer MODIFY = 53;

    /**
     * The Constant CUSTOM.
     */
    public static final Integer CUSTOM = 3000;

}
