
package ua.bionic.turko.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import ua.bionic.turko.commands.AccountCommand;
import ua.bionic.turko.daointerfaces.ICommand;
import ua.bionic.turko.commandsadmin.AccountAdminCommand;
import ua.bionic.turko.commandsadmin.AddPublicationCommand;
import ua.bionic.turko.commandsadmin.AddPublicationConfirmCommand;
import ua.bionic.turko.commandsadmin.DeletePublicationConfirmCommand;
import ua.bionic.turko.commands.BuyCommand;
import ua.bionic.turko.commands.BuyErrorCommand;
import ua.bionic.turko.commands.InfoCommand;
import ua.bionic.turko.commandsadmin.ListPublAdminCommand;
import ua.bionic.turko.commands.ListPublCommand;
import ua.bionic.turko.commands.LoginCommand;
import ua.bionic.turko.commands.NoCommand;
import ua.bionic.turko.commands.OrdersCommand;
import ua.bionic.turko.commands.HistoryCommand;
import ua.bionic.turko.commands.LanguageEnCommand;
import ua.bionic.turko.commands.LanguageRuCommand;
import ua.bionic.turko.commands.OrdersCancelCommand;
import ua.bionic.turko.commands.SubscribeCommand;
import ua.bionic.turko.commandsadmin.EditPublicationCommand;
import ua.bionic.turko.commandsadmin.EditPublicationConfirmCommand;
import ua.bionic.turko.commandsadmin.LanguageEnAdminCommand;
import ua.bionic.turko.commandsadmin.LanguageRuAdminCommand;


class RequestHelper {
    private static RequestHelper instance = null;
    HashMap<String,ICommand> commands = new HashMap<String,ICommand>();
    public static final Logger LOG=Logger.getLogger(RequestHelper.class.getName());  

    private RequestHelper() { //bind commands
      commands.put("login", new LoginCommand());
      commands.put("list", new ListPublCommand());
      commands.put("list_admin", new ListPublAdminCommand());
      commands.put("info", new InfoCommand());
      commands.put("subscribe", new SubscribeCommand());
      commands.put("account", new AccountCommand());
      commands.put("account_admin", new AccountAdminCommand());
      commands.put("orders", new OrdersCommand());
      commands.put("orders_cancel", new OrdersCancelCommand());
      commands.put("history", new HistoryCommand());
      commands.put("buy", new BuyCommand());
      commands.put("buy_error", new BuyErrorCommand());
      commands.put("add", new AddPublicationCommand());
      commands.put("add_confirm", new AddPublicationConfirmCommand());
      commands.put("edit", new EditPublicationCommand());
      commands.put("edit_confirm", new EditPublicationConfirmCommand());
      commands.put("lang_en", new LanguageEnCommand());
      commands.put("lang_ru", new LanguageRuCommand());
      commands.put("delete", new DeletePublicationConfirmCommand());
      commands.put("lang_admin_en", new LanguageEnAdminCommand());
      commands.put("lang_admin_ru", new LanguageRuAdminCommand());      
    }    
    
    public ICommand getCommand(HttpServletRequest request){
        String action = request.getParameter("command");
        LOG.warn("action: " + action); // get action command
        ICommand command = commands.get(action);
        if (command == null){ // if no known command
            LOG.warn("Error command: action not found");
            command = new NoCommand();
        }
        return command;       
    }
    
    public static RequestHelper getInstance(){
        if (instance == null){
            instance = new RequestHelper();
        }
        return instance;
    }
    
}    