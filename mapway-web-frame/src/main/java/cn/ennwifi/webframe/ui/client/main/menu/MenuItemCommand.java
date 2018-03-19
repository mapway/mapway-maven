package cn.ennwifi.webframe.ui.client.main.menu;

import cn.ennwifi.webframe.ui.client.ClientContext;
import cn.ennwifi.webframe.ui.client.event.EventTopics;
import cn.ennwifi.webframe.ui.shared.repository.S_RESOURCEObj;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;


/**
 * 菜单命令执行.
 *
 * @author zhangjianshe
 */
public class MenuItemCommand implements ScheduledCommand {


    /**
     * The data.
     */
    S_RESOURCEObj data;

    /**
     * 构造一个对菜单项的执行.
     *
     * @param au the au
     */
    public MenuItemCommand(S_RESOURCEObj au) {
    data = au;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.core.client.Scheduler.ScheduledCommand#execute()
   */
  @Override
  public void execute() {
    if (data != null) {
      ClientContext.getContext().postTopic(EventTopics.SWITCH_MAIN_FRAME_SUB_MODULE, 0, data);
    }
  }

}
